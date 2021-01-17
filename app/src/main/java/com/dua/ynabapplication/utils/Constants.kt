package com.dua.ynabapplication.utils

import com.dua.ynabapplication.BuildConfig

class Constants private constructor() {

    companion object {
        const val REDIRECT_URI = "http://${BuildConfig.HOSTNAME}"
        const val DEMO_REDIRECT_URI_ = "http://localhost"
        const val OAUTH_API_ENDPOINT = "oauth"
        const val TRANSACTION_API_ENDPOINT = "v1/budgets/{budget_id}/transactions"
        const val ACCOUNTS_API_ENDPOINT = "v1/budgets/{budget_id}/accounts"
            const val SINGLE_ACCOUNT_API_ENDPOINT ="v1/budgets/{budget_id}/accounts/{account_id}"
        const val CATEGORY_API_ENDPOINT = "v1/categories"
            const val AVAILABLE_BUDGET_API_ENDPOINT = "v1/budgets"
            const val SINGLE_BUDGET_API_ENDPOINT = "v1/budgets/{budget_id}"

        const val DB_NAME = "ynab.db"
    }
}