 private fun sendResultBack() {
        val inputText = inputEditText.text.toString().trim()
        
        // Validate input
        if (validateInput(inputText)) {
            // Create an intent to hold our result
            val resultIntent = Intent()
            // Put the input text as an extra
            resultIntent.putExtra("INPUT_TEXT", inputText)
            // Set the result as OK and include the intent
            setResult(RESULT_OK, resultIntent)
            // Finish the activity to return to MainActivity
            finish()
        }
    }
    
    private fun validateInput(input: String): Boolean {
        // Check if input is empty
        if (TextUtils.isEmpty(input)) {
            inputLayout.error = "Input cannot be empty"
            return false
        }
