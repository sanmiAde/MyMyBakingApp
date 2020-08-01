package com.sanmidev.mybakingapp.di.component

import com.sanmidev.mybakingapp.di.scope.ActivityScope
import com.sanmidev.mybakingapp.feature.recipes.RecipesFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    fun inject(recipesFragment: RecipesFragment)
}