package com.android.wombatcodingchallenge.util

import java.io.IOException

/**
 * Exception class to throw in case of any error on the network call
 * */
class ApiException(message: String) : IOException(message)

/**
 * Exception class to throw in case of no internet connectivity
 * */
class NoInternetException(message: String) : IOException(message)