ViewModel(앱에 필요한 데이터를 저장하고 관리하는 객체!) 사용!! 

Hilt + Repository 내용도 있음

의존성 주입(DI)는 클래스 내부에서 객체를 직접 생성하는 대신, 외부에서 객체를 주입받는 방식이다.

RoomDatabase 내용도 있음

Coroutine 내용도 있음

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


![스크린샷 2024-11-05 오후 1 40 39](https://github.com/user-attachments/assets/c89b573f-fa2d-4f44-802a-f16c8345e4e9)
