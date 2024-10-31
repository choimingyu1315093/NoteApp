ViewModel(앱에 필요한 데이터를 저장하고 관리하는 객체!) 사용!! 


TextField 정석! 

@Composable
fun NoteInputText(
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {},
    modifier: Modifier = Modifier
){
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.colors(

        ),
        maxLines = maxLine,
        label = {
            Text(
                text = label
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier
    )
}
