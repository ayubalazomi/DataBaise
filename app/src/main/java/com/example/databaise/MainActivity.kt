package com.example.databaise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.RowScopeInstance.align
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.databaise.destinations.*
import com.example.databaise.model.Contact
import com.example.databaise.ui.AddContactviewmodel
import com.example.databaise.ui.Contactviewmodel
import com.example.databaise.ui.theme.DataBaiseTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DataBaiseTheme {
                DestinationsNavHost(navGraph =NavGraphs.root)
            }
        }
    }
}`

@Composable
@Destination
@RootNavGraph(start = true)
fun home(nav: DestinationsNavigator,viewModel: Contactviewmodel= hiltViewModel()) {
    val info = remember { mutableStateOf(false) }
    // var chsave = remember { mutableStateOf(false)}
    Box() {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Row(
                modifier = Modifier
                    .padding(8.dp), horizontalArrangement = Arrangement.Start
            ) {
                Text(modifier = Modifier, text = "Notes ", fontSize = 43.sp, color = Color.White)
                Spacer(modifier = Modifier.padding(80.dp, 0.dp))
                Button(
                    onClick = { nav.navigate(SeachDestination) }, modifier = Modifier
                        .clip(CircleShape)
                        .requiredHeight(50.dp)
                        .requiredWidth(50.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_search_24),
                        contentDescription = ""
                    )

                }

                Spacer(modifier = Modifier.padding(4.dp, 0.dp))
                Button(
                    onClick = { info.value = true }, modifier = Modifier
                        .clip(CircleShape)
                        .requiredHeight(50.dp)
                        .requiredWidth(50.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_info_24),
                        contentDescription = ""
                    )

                }
            }
            if (viewModel.contacts.isEmpty()) {
                Image(
                    painter = painterResource(id = R.drawable.rafiki),
                    contentDescription = "",
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(top = 250.dp)
                        .size(250.dp, 250.dp)
                )
            } else {
                Lazenote(viewModel, nav)
            }

        }

        FloatingActionButton(onClick = { nav.navigate(AddnotesDestination) },
            modifier = Modifier
                .align(BottomEnd).padding(16.dp,16.dp), backgroundColor = Color.Gray

        ) {

            Icon(

                painter = painterResource(R.drawable.baseline_post_add_24),
                contentDescription = ""
            )
        }
    }

    if (info.value) {
        AlertDialogSample(info)
    }
}


@Destination
@Composable
fun Seach(){

    val bahet = remember {
        mutableStateOf("")
    }

     Column(modifier = Modifier
         .fillMaxSize()
         .background(Color.Black)) {
         Row(modifier = Modifier.padding(16.dp,30.dp) ,
             horizontalArrangement = Arrangement.Center,
             verticalAlignment = Alignment.Top) {
             OutlinedTextField(
                 value = bahet.value,
                 onValueChange = { bahet.value = it },
                 label = { Text(text = "Search ", color = Color.White) },
                 placeholder = {Text (text = "Search in your notes", color = Color.White)},
                 singleLine = true,
                 modifier =   Modifier.fillMaxWidth(),
                 shape = RoundedCornerShape(50.dp)
             )

         }
            Column(horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.align(CenterHorizontally))

                Image(painter = painterResource(R.drawable.cuate), contentDescription ="" ,
                    Modifier
                        .width(280.dp)
                        .height(280.dp)
                        .align(CenterHorizontally))}



                Text(text = "File not found. Try searching again.", fontSize = 20.sp, color = Color.White)
            }


    }

@Composable
fun AlertDialogSample(info: MutableState<Boolean>) {
    MaterialTheme {
        Column {
            var openDialog = remember { mutableStateOf(true) }



            if (openDialog.value) {

                AlertDialog(
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onCloseRequest.
                        info.value = false
                        openDialog.value = info.value
                    },
                    text = {
                        Column() {
                            Text(text = "Redesigned by-")
                            Text(text = "iiiustrations-")
                            Text(text = "Icons-")
                            Text(text = "Font-")
                        }
                    }, buttons = { openDialog.value = true })


            }
        }
    }
}


@Destination
@Composable
fun Addnotes(nav: DestinationsNavigator, addviewmodel:AddContactviewmodel= hiltViewModel()) {

    val titel = remember {
        mutableStateOf("")}

    val tttext = remember {
        mutableStateOf("")}

    val backdig = remember {
        mutableStateOf(false)}
    Column(modifier = Modifier.fillMaxSize()) {

        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = { nav.navigate(homeDestination)}, modifier = Modifier
                    .padding(16.dp)
            )
            {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "",
                )
            }
            Spacer(modifier = Modifier.padding(80.dp, 0.dp))
            IconButton(
                onClick = { }, modifier = Modifier
                    .padding(16.dp)

            )
            {
                Icon(
                    painter = painterResource(R.drawable.baseline_adjust_24),
                    contentDescription = "",
                )
            }
            Spacer(modifier = Modifier.padding(1.dp, 0.dp))
            IconButton(
                onClick = {addviewmodel.addContact(titel.value,tttext.value)
                    nav.popBackStack()
                          }, modifier = Modifier
                    .padding(16.dp)
            )
            {
                Icon(
                    painter = painterResource(R.drawable.baseline_save_24),
                    contentDescription ="",
                )
            }
        }

       Row(modifier = Modifier
           .fillMaxWidth()
           ) {
           TextField(value =titel.value , onValueChange = {titel.value=it}, modifier = Modifier.fillMaxWidth(),
               label = { Text(text = "titel ", color = Color.Black, fontSize = 25.sp) },
               placeholder = {Text (text = "titel", color = Color.Black)})
       }

            TextField(value =tttext.value , onValueChange = {tttext.value=it}, modifier = Modifier.fillMaxSize(),
                label = { Text(text = "titel ", color = Color.Black) },
                placeholder = {Text (text = "titel", color = Color.Black, fontSize = 24.sp)})
    }



}


@Composable
@Destination
fun Viewnote(nav: DestinationsNavigator,item:Contact,viewModel: AddContactviewmodel= hiltViewModel()){
    var rdtitel by remember { mutableStateOf(item.titel) }
    var ednas by remember { mutableStateOf(item.inputnotes) }



    Column(modifier = Modifier
        .fillMaxSize()
        .padding(1.dp)
        .background(Color.White)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)) {
            IconButton(onClick = {
                viewModel.deleet(item)
                nav.navigateUp()

            }) { Icon(
                painter =painterResource(R.drawable.baseline_delete_24)
                , contentDescription ="" )

            }
            Spacer(modifier = Modifier.padding(1.dp))
            IconButton(onClick = {
                item.titel=rdtitel
                item.inputnotes=ednas
                viewModel.edit(item)
            nav.navigateUp()
            }) { Icon(
                painter =painterResource(R.drawable.baseline_edit_24)
                , contentDescription ="" )

            }


        }

        TextField(value = rdtitel, onValueChange = { new -> rdtitel=new } )
        TextField(value = ednas, onValueChange ={new -> ednas=new})


    }

}
@Destination
@Composable
fun AlertDialogsave(opendigsave:MutableState<Boolean>) {
    MaterialTheme {
        Column {
            val openDialog = remember { mutableStateOf(false)  }
            openDialog.value = true
            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onCloseRequest.
                        openDialog.value = false
                        opendigsave.value=false
                    },
                    title = {
                        Text(text = "Save change")
                    },
                    text = {
                        Text("are you shur save changed ? ")
                    },
                    confirmButton = {
                        Button(

                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("Save", color = Color.Black,modifier = Modifier.background(Color.Green))
                        }
                    },
                    dismissButton = {
                        Button(

                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("Discard" , color = Color.Black, modifier = Modifier.background(Color.Red))
                        }
                    }
                )
            }
        }

    }
}

@Composable
fun Lazenote(viewModel: Contactviewmodel,nav: DestinationsNavigator) {


    val colaaaar= listOf(
        Color(0xfffd99ff),
        Color(0xfffd69aa),
        Color(0xfffff9ff),
        Color(0xfffdabcc),
        Color(0xffff929e),
        Color(0xffbc9cff),
        Color(0xff91f48f)
        )
LazyColumn(
modifier = Modifier
    .fillMaxSize().background(Color.Black)
    .padding(3.dp, 3.dp)

) {
    items(viewModel.contacts) { person ->
        Surface(modifier = Modifier
            .clickable {
            nav.navigate(ViewnoteDestination(person))
        }) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .background(colaaaar.random())
            ) {
                Spacer(modifier = Modifier.height(1.dp))
                Text(text = "${person.titel} \n ${person.inputnotes} ")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
}