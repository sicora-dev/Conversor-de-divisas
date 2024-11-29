package com.mrh.conversordivisas

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mrh.conversordivisas.ui.theme.ConversorDivisasTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.FontScaling
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil3.size.Size
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val viewModel = AppViewModel()
            val navBarItems = listOf(NavBarElements.INICIO, NavBarElements.CONVERSIONES)
            ConversorDivisasTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            navBarItems.forEach{item ->
                                NavigationBarItem(
                                    selected = navBackStackEntry?.destination?.hierarchy?.any { destination -> destination.route == item.route} == true,
                                    onClick = {
                                        navController.navigate(item.route)
                                    },
                                    label = {
                                        Text(text = item.title)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = "Icono"
                                        )
                                    }
                                )
                            }

                        }
                    }

                ) { innerPadding ->
                    NavigationHost(navController = navController,viewModel = viewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    fun NavigationHost(navController: NavHostController, viewModel: AppViewModel, modifier: Modifier){
        NavHost(
            navController = navController,
            startDestination = NavBarElements.INICIO.route
        ){
            composable(route = NavBarElements.INICIO.route) {
                HomeView(modifier,viewModel)
            }
            composable(route = NavBarElements.CONVERSIONES.route) {
                ConversionesView(modifier,viewModel)
            }
        }
    }

    @Composable
    fun ConversionesView(modifier: Modifier, viewModel: AppViewModel){
        val convertValues = ConvertValues.entries
        Column(modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())) {
            convertValues.forEach{ value ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .height(90.dp),
                    ){
                    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(painter = painterResource(value.image), contentDescription = "", modifier = Modifier.padding(end= 8.dp).width(120.dp).height(150.dp), contentScale = ContentScale.FillBounds)
                            Text(text = value.divisa, fontWeight = FontWeight.Bold)
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(end = 8.dp) ){
                            Text("Valor: ")
                            Text(value.valor.toString(), fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("MutableCollectionMutableState")
    @Composable
    fun HomeView(modifier: Modifier = Modifier, viewModel: AppViewModel) {

        val divisas = ConvertValues.entries
        var divisa1 by remember { mutableStateOf("€")}
        var divisa2 by remember { mutableStateOf("$")}
        var divisa1q by remember { mutableDoubleStateOf(0.92) }
        var divisa2q by remember { mutableDoubleStateOf(1.0) }
        var expanded by remember { mutableStateOf(false)}
        var expanded2 by remember { mutableStateOf(false)}
        var quantity by remember { mutableStateOf("") }
        var conversion by remember { mutableDoubleStateOf(0.0) }
        val historial by viewModel.history.collectAsState()

        Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Card(modifier= Modifier.padding(16.dp,0.dp).fillMaxWidth()) {
                Text(modifier= Modifier.padding(20.dp), text = "Convierte divisas", fontSize = 36.sp, fontWeight = FontWeight.ExtraBold)
                TextField(modifier = Modifier.padding(16.dp, 0.dp),
                    value = quantity,
                    onValueChange = {
                        quantity = it
                    },
                    placeholder = {
                        Text(text = "numero")
                    },

                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                    leadingIcon = {
                        Text(text = divisa1)
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            expanded2 = !expanded2
                        }) {
                            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
                            DropdownMenu(
                                expanded = expanded2,
                                onDismissRequest = { expanded2 = false },
                            ) {
                                divisas.forEach { divisa ->
                                    DropdownMenuItem(
                                        text = { Text(divisa.divisa) },
                                        onClick = {
                                            expanded2 = false
                                            divisa1 = divisa.simbolo
                                            divisa1q = divisa.valor
                                        },
                                        leadingIcon = {
                                            Text(text = divisa.simbolo)
                                        }
                                    )
                                }

                            }
                        }
                    }
                )

                Row(modifier= Modifier.fillMaxWidth().padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(modifier = Modifier.padding(6.dp,16.dp,0.dp, 0.dp), text = "Convertir a: $divisa2", fontWeight = FontWeight.Bold)
                        IconButton(

                            onClick = {
                                expanded = !expanded
                            },


                            ) {
                            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                            ) {
                                divisas.forEach { divisa ->
                                    DropdownMenuItem(
                                        text = { Text(divisa.divisa) },
                                        onClick = {
                                            expanded = false
                                            divisa2 = divisa.name
                                            divisa2q = divisa.valor
                                        },
                                        leadingIcon = {
                                            Text(text = divisa.simbolo)
                                        }
                                    )
                                }

                            }
                        }
                    }

                }
                Row(modifier= Modifier.padding(16.dp)){
                    Button(
                        modifier = Modifier.padding(6.dp,0.dp),
                        onClick = {
                            conversion = Convert(
                                divisa1,
                                divisa2,
                                divisa1q,
                                divisa2q,
                                quantity.toDouble(),
                                viewModel
                            )

                        },

                        ) {
                        Text(text = "Convertir")
                    }

                    Button(
                        modifier = Modifier.padding(6.dp,0.dp),
                        onClick = {

                            conversion = 0.0
                        },

                        ) {
                        Text(text = "Limpiar")
                    }
                }
            }

            Card(modifier= Modifier.padding(16.dp).fillMaxWidth()) {
                Text(modifier= Modifier.padding(16.dp),text = "Resultado: ${conversion.toString().split('.')[0]+'.'+conversion.toString().split('.')[1][0]}", fontWeight = FontWeight.Bold)
            }
            if(historial.isNotEmpty()){
                Card(modifier= Modifier.padding(16.dp).fillMaxWidth().verticalScroll(rememberScrollState())) {
                    Text(modifier= Modifier.padding(16.dp),text = "Historial: ", fontWeight = FontWeight.Bold)
                    historial.reversed().forEach { historyitem ->
                        Text(modifier= Modifier.padding(16.dp),text = "${historyitem.simbolo1} ${historyitem.quantity} = ${historyitem.simbolo2} ${historyitem.resultado}")
                        HorizontalDivider()
                    }
                }
            }


        }

    }

    fun Convert(divisa1Simbolo: String, divisa2Simbolo: String, divisa1: Double, divisa2: Double, quantity: Double, viewModel: AppViewModel): Double {
        val newcambio = Cambio(
            divisa1Simbolo,
            divisa2Simbolo,
            quantity,
            divisa2 * quantity / divisa1
        )
        viewModel.addCambio(newcambio)
        return divisa2 * quantity / divisa1
    }


}