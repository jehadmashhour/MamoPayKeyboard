MamoPayKeyboard
=================

This is an Android project for showing how to do a custom keyboard view

USAGE
-----

You can use the keyboard and decimal text view by adding the DecimalTextView and NumberKeyboardView in your xml.

XML
-----

```xml
<com.example.keyboard.views.DecimalTextView
    android:id="@+id/decimalTextView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:textAlignment="center"
    android:textSize="@dimen/_25ssp"
    app:layout_constraintTop_toTopOf="parent"
    app:currency="@string/aed"
    app:maxInputLength="13"
    app:amountHighlightColor="@color/colorBlack"
    app:amountDimmingColor="@color/colorGray"
    app:maxDecimalDigit="2" />
```

You can use the following properties in your XML to change your DecimalTextView.

| Properties                       | Type                                                         | Default          |
| -------------------------------- | ------------------------------------------------------------ | ---------------- |
| `app:currency`                   | string                                                       | empty            |
| `app:maxDecimalDigit`            | integer                                                      | 1                |
| `app:maxInputLength`             | integer                                                      | 13               |
| `app:amountHighlightColor`       | color                                                        | colorBlack       |
| `app:amountDimmingColor`         | color                                                        | colorGray        |

```xml
<com.example.keyboard.views.NumberKeyboardView
    android:id="@+id/numberKeyboardView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="@dimen/_30sdp"
    app:decimalTextViewId="@id/decimalTextView"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/decimalTextView" />
```

You can use the following properties in your XML to change your NumberKeyboardView.

| Properties                       | Type                                                         | Default          |
| -------------------------------- | ------------------------------------------------------------ | ---------------- |
| `app:decimalTextViewId`          | reference                                                    | 0                |


Code
-----
You can use the following codes .

```
  //Callbacks execute when clicking on the keyboardview buttons
  numberKeyboardView.setNumberKeyboardListener(object : NumberKeyboardListener {
                override fun onNumberButtonClicked(value: CharSequence) {
                    //Do your implementation
                }

                override fun onClearButtonClicked() {
                    //Do your implementation
                }
            })

  //Set your DecimalTextView
  numberKeyboardView.setDecimalTextView(decimalTextView)

  //Add text value
  decimalTextView.addText()

  //Clear text value
  decimalTextView.clearText()

  //Get the entered number
  decimalTextView.enteredNumber

```
