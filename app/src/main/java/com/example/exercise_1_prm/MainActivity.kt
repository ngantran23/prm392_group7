package com.example.exercise_1_prm

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var dateInput: EditText
    private lateinit var nationalityInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var maleCheckbox: CheckBox
    private lateinit var femaleCheckbox: CheckBox
    private lateinit var otherCheckbox: CheckBox
    private lateinit var datePickerImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameInput = findViewById(R.id.name_input)
        dateInput = findViewById(R.id.date_input)
        nationalityInput = findViewById(R.id.nationality_input)
        emailInput = findViewById(R.id.email_input)
        phoneInput = findViewById(R.id.phone_input)
        maleCheckbox = findViewById(R.id.cbMale)
        femaleCheckbox = findViewById(R.id.cbFemale)
        otherCheckbox = findViewById(R.id.cbOther)
        datePickerImageView = findViewById(R.id.ivDatePicker)

        datePickerImageView.setOnClickListener {
            showDatePickerDialog()
        }

        val loginButton: Button = findViewById(R.id.login_btn)
        loginButton.setOnClickListener {
            validateInputs()
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                dateInput.setText(selectedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun validateInputs() {
        val name = nameInput.text.toString()
        val dateOfBirth = dateInput.text.toString()
        val nationality = nationalityInput.text.toString()
        val email = emailInput.text.toString()
        val phone = phoneInput.text.toString()

        if (name.isEmpty() || dateOfBirth.isEmpty() || nationality.isEmpty() ||
            email.isEmpty() || phone.isEmpty()
        ) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin.", Toast.LENGTH_SHORT).show()
            return
        }

        if (!isEmailValid(email)) {
            Toast.makeText(this, "Vui lòng nhập đúng định dạng email.", Toast.LENGTH_SHORT).show()
            return
        }

        if (!isPhoneValid(phone)) {
            Toast.makeText(this, "Vui lòng nhập đúng định dạng số điện thoại.", Toast.LENGTH_SHORT).show()
            return
        }

        if (!isGenderSelected()) {
            Toast.makeText(this, "Vui lòng chọn giới tính.", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPhoneValid(phone: String): Boolean {
        return android.util.Patterns.PHONE.matcher(phone).matches()
    }

    private fun isGenderSelected(): Boolean {
        return maleCheckbox.isChecked || femaleCheckbox.isChecked || otherCheckbox.isChecked
    }
}
