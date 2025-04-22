package com.codeblin.myapplication.ui.screens.todoList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codeblin.archcore.presentation.ArchCoreScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun TodoScreen(viewModel: TodoViewModel = koinViewModel()) {
    val state = viewModel.uiState.collectAsState().value
    ArchCoreScreen(
        isLoading = false,
        errorMessage = null,
        topBarTitle = "Item List", // Example top bar title
        onTopBarAction = { /* Handle top bar action, like navigation */ },
    ) {
        Content(state, processIntent = viewModel::processIntent)
    }
}

@Composable
fun Content(
    state: TodoUiState,
    processIntent: (TodoUiIntent) -> Unit,
) {
    Column(Modifier.padding(16.dp)) {
        var text by remember { mutableStateOf("") }

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter task") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (text.isNotBlank()) {
                    processIntent(TodoUiIntent.AddTodo(text))
                    text = "" // Clear input after adding
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(state.todos) { todo ->
                TodoItemView(
                    todo,
                    onToggle = {
                        processIntent(TodoUiIntent.ToggleTodo(todo.id))
                    },
                    onDelete = {
                        processIntent(TodoUiIntent.DeleteTodo(todo.id))
                    },
                    onClick = {
                        processIntent(TodoUiIntent.Navigate.ToDetails(todo.id.toString()))
                    }
                )
            }
        }

        LaunchedEffect(Unit) {
            processIntent(TodoUiIntent.GetTodos)
        }
    }
}

@Composable
fun TodoItemView(
    todo: TodoItem,
    onToggle: () -> Unit,
    onDelete: () -> Unit,
    onClick: (id: String) -> Unit,
) {
    // Row to display the todo item
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Checkbox to toggle the completion state of the todo item
        Checkbox(
            checked = todo.isCompleted,
            onCheckedChange = { onToggle() }
        )

        // Text to display the todo title
        Text(
            text = todo.text,
            modifier = Modifier
                .weight(1f) // Allow text to take up remaining space
                .padding(start = 16.dp)
                .clickable { onClick(todo.id.toString()) },
            style = MaterialTheme.typography.bodyLarge,
            color = if (todo.isCompleted) Color.Gray else Color.Black
        )

        // Delete button
        IconButton(onClick = { onDelete() }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Todo",
                tint = Color.Red
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewTodoItemView() {
    val todo = TodoItem(id = 1, text = "Sample Todo Item", isCompleted = false)

    TodoItemView(
        todo = todo,
        onToggle = { /* Simulate toggle action */ },
        onDelete = { /* Simulate delete action */ },
        onClick = { /* Simulate click action */ }
    )
}


@Composable
@Preview(showBackground = true)
fun PreviewTodoListScreen() {
    // Mock a sample TodoViewModel with a list of todos
    val sampleTodos = listOf(
        TodoItem(id = 1, text = "Buy groceries", isCompleted = false),
        TodoItem(id = 2, text = "Walk the dog", isCompleted = true),
        TodoItem(id = 3, text = "Finish homework", isCompleted = false)
    )

    Content(TodoUiState(todos = sampleTodos)) {}
}


