package com.inegru.android.atelieruldigital.helloworld.week8;

/**
 * .
 */
interface FetchUsersCallback<T> {

    /**
     * Indicates that the callback handler needs to update its appearance or information based on
     * the result of the task. Expected to be called from the main thread.
     */
    void updateFromRequest(T result);

    void updateFromFailedRequest(String message);

    /**
     * Get the device's active network status in the form of a NetworkInfo object.
     */
    boolean isConnected();

    /**
     * Indicates that the operation has finished. This method is called even if the operation
     * hasn't completed successfully.
     */
    void finishFetchingUsers();
}
