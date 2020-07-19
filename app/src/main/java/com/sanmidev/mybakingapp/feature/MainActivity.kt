package com.sanmidev.mybakingapp.feature

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.sanmidev.mybakingapp.MyBakingApplication
import com.sanmidev.mybakingapp.R
import com.sanmidev.mybakingapp.di.component.ActivityComponent
import com.sanmidev.mybakingapp.di.component.DaggerApplicatoinComponent

class MainActivity : AppCompatActivity() {

    lateinit var activityComponent: ActivityComponent
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        activityComponent =
            (application as MyBakingApplication).applicatoinComponent.activityComponent().create()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navFragment) as NavHostFragment

    }
}