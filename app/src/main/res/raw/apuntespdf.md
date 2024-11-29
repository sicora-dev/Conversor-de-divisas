# Apuntes Android: Jetpack Compose

## Contenedores

Columna
```kotlin
import androidx.compose.foundation.layout.Column

Column(){
    //Aqui va el contenido
}
```

Fila
```kotlin
import androidx.compose.foundation.layout.Row

Row(){
        //Aqui va el contenido
}
```

Box
```kotlin
import androidx.compose.foundation.layout.Box

Box(){

}
```

Grid Verical
```kotlin
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells

LazyVerticalGrid(
        // Numero de columnas
        columns = GridCells.Fixed(3)
) {
    //Aqui va el contenido
}
```
Tarjeta
```kotlin
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.Color

Card(
    //Cambiar el color
    colors = CardDefaults.cardColors(
        containerColor = Color.Red
    ),
    //Accion al hacer click sobre el
    onClick = {

    }
){
    //Contenido
}
```

## Contenido

Texto
```kotlin
import androidx.compose.material3.Text

Text(
        text = "Hello!"
    )
```

Campo de texto
```kotlin
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

var texto by remember { mutableStateOf("") }

TextField(
    value = texto,
    onValueChange = { textoEscrito ->
        texto = textoEscrito
    },
    // Poner teclado solo numeros
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    //Iconos dentro del TextField (Si quereis hacer click sobre el icono, debe ser un IconButton
    leadingIcon = {
        
    },
    trailingIcon = {
        
    }
)

```

Menu Desplegable

```kotlin
DropdownMenu(
    expanded = expanded,
    onDismissRequest = { expanded = false },
) {
    DropdownMenuItem(
        text = { Text("texto") },
        onClick = {
            expanded = false
        },
        leadingIcon = {
            Text(imageVector = , contentDescription = null)
        }
    )
}
```

Linea Divisoria
```kotlin
import androidx.compose.material3.HorizontalDivider

HorizontalDivider()

```


Boton

```kotlin
import androidx.compose.material3.Button

Button(
    onClick = {
        
    }
){
    
}

```

Icono
```kotlin
import androidx.compose.material.icons.Icons

Icon(imageVector = Icons.Filled.Home, contentDescription = "Icono")

```

## Navegacion de la Aplicacion

```kotlin
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation


NavHost(
    navController = navController,
    startDestination = "",
) {
    navigation(
        route = "",
        startDestination = ""
    ){
        composable(route = "") {
            SomeView()
        }
    }
    composable(route = "") {
        HomeView()
    }
    
}

```


## Top App Bar

```kotlin
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem


@OptIn(ExperimentalMaterial3Api::class)
TopAppBar(
    title = {
        Text("Titulo")
    }
)

```

## Navigation Bar

```kotlin
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem


NavigationBar {
    NavigationBarItem(
        selected = Boolean,
        onClick = {

        },
        label = {
            Text("")
        },
        icon = {
            Icon(
                imageVector = ,
                contentDescription = "Icono"
            )
        }
    )
}
```
