package com.example.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val et_name: EditText = findViewById(R.id.et_name)
        val et_age:EditText = findViewById(R.id.et_age)
        val radio_g : RadioGroup = findViewById(R.id.radio_g)
        val male : RadioButton = findViewById(R.id.r_male)
        val sp_countries : Spinner = findViewById(R.id.sp_countries)
        val sw_graduate :Switch = findViewById(R.id.sw_graduate)
        val sb_satisfide :SeekBar = findViewById(R.id.sb_satisfide)
        val save :Button = findViewById(R.id.btn_save)

        val isGraduate:String = sw_graduate.isActivated.toString()

        var satisfide:String  = ""
        sb_satisfide.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                satisfide = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        save.setOnClickListener {

            val name:String = et_name.text.toString()
            val age:String = et_age.text.toString()
            val country:String = sp_countries.selectedItem.toString()


            val gender = radio_g.checkedRadioButtonId
            val g :String
            if (gender == male.id)
                g = "Male"
            else
                g ="Female"

            Toast.makeText(this ,"Name :"+ name + " Age : " +age +". Country : "+country+"Gender : "+g+"Graduate : "+isGraduate+"Satisfide : "+satisfide , Toast.LENGTH_LONG).show()
        }

    }
}