package com.dirisa0302.zinckey

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.DataOutputStream

class MainActivity : AppCompatActivity() {

    private var isCheatEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnToggle = findViewById<Button>(R.id.btnToggle)

        btnToggle.setOnClickListener {
            isCheatEnabled = !isCheatEnabled
            if (isCheatEnabled) {
                btnToggle.text = "ĐANG BẬT (TEST ROOT)"
                btnToggle.setBackgroundColor(0xFF00C853.toInt()) // Màu xanh
                runRootTest()
            } else {
                btnToggle.text = "BẬT CHEAT"
                btnToggle.setBackgroundColor(0xFFFF4444.toInt()) // Màu đỏ
                Toast.makeText(this, "Đã tắt trạng thái test!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun runRootTest() {
        try {
            val process = Runtime.getRuntime().exec("su")
            val outputStream = DataOutputStream(process.outputStream)
            outputStream.writeBytes("id\n")
            outputStream.writeBytes("exit\n")
            outputStream.flush()
            process.waitFor()

            Toast.makeText(this, "Đã gửi lệnh Root thành công!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Lỗi yêu cầu Root: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
