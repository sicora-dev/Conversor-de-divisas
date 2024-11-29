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
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
)

```