package com.devshish.recycler.model

/**
 * Represents RecyclerView UI model
 *
 * Should be used only in UI layer
 */
interface ListItem {

    /**
     * Unique value, representing id of current item
     */
    val itemId: String
}
