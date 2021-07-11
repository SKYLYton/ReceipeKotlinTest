package com.receipe.fragments.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.receipe.R
import com.receipe.databinding.FragmentAuthBinding
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher


class AuthFragment : Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding: FragmentAuthBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAuthBinding.inflate(inflater, container, false)

        _binding = binding

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initControls()
    }

    private fun initControls() {

        initEditTextPhone()

        binding.editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                clearErrorPassword()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                makeButtonSignInEnabled()
            }
        })

        binding.buttonSignIn.setOnClickListener {
            val isCorrectPhone = checkPhoneNumber(binding.editTextPhone.text.toString())
            val isCorrectPassword = checkPassword(binding.editTextPassword.text.toString())

            if (isCorrectPassword && isCorrectPhone) {
                Toast.makeText(context, "Вы успешно авторизовались", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initEditTextPhone() {
        binding.editTextPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                clearErrorPhone()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                makeButtonSignInEnabled()
            }
        })

        val mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
        val watcher = MaskFormatWatcher(mask)
        watcher.installOn(binding.editTextPhone)

        binding.editTextPhone.setSelection(binding.editTextPhone.length())
    }

    private fun checkPhoneNumber(s: String): Boolean {
        if (s.length == 18) {
            clearErrorPhone()
            return true
        }

        binding.textInputLayoutPhone.error = getString(R.string.enter_number_phone_error_length)
        return false

        /* binding.textInputLayoutPhone.error = getString(R.string.enter_number_phone_error)
        return false*/
    }

    private fun checkPassword(s: String): Boolean {
        if (s.isNotEmpty() && s.length >= 8) {
            clearErrorPassword()
            return true
        }

        binding.textInputLayoutPassword.error = getString(R.string.enter_password_error_length)
        return false
    }

    private fun makeButtonSignInEnabled() {
        if (binding.editTextPhone.text == null || binding.editTextPassword.text == null) return

        var b = false

        if (binding.editTextPhone.text!!.length >= 18 && binding.editTextPassword.text!!.length >= 8) b = true

        binding.buttonSignIn.isEnabled = b
    }

    private fun clearErrorPhone() {
        binding.textInputLayoutPhone.error = ""
    }

    private fun clearErrorPassword() {
        binding.textInputLayoutPassword.error = ""
    }
}