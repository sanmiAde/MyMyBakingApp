package com.sanmidev.mybakingapp.utils

import androidx.navigation.NavController
import androidx.navigation.NavDirections


/***
 * Extension method used to safely navigate between fragment.
 * Make sure the current destination exists, by checking if the passed destination’s action id resolves to an action on the current destination. This avoids attempting to navigate on non-existent destinations
 * @param navDirections is the directon to go to
 * @author oluwasanmi Aderibigbe
 */

fun NavController.navigateSafely(
    navDirections: NavDirections
) {
    this.currentDestination?.getAction(navDirections.actionId)?.let {
        this.navigate(navDirections)
    }

}