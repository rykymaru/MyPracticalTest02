package com.example.teodor.mypracticaltestnew;

import android.provider.SyncStateContract;
import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import cz.msebera.android.httpclient.client.ClientProtocolException;

public class Server extends Thread {
    ServerSocket serverSocket;
    int port;

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    HashMap<String, WeatherForecastInformation> data = null;
    public Server(int port) {
        this.port = port;
        this.data = new HashMap<>();
        try {
            serverSocket = new ServerSocket(this.port);
        } catch (IOException ioException) {
            Log.e("Error", "An exception has occurred: " + ioException.getMessage());
            if (true) {
                ioException.printStackTrace();
            }
        }
    }

    public synchronized void setData(String city, WeatherForecastInformation weatherForecastInformation) {
        this.data.put(city, weatherForecastInformation);
    }

    public synchronized HashMap<String, WeatherForecastInformation> getData() {
        return data;
    }

    @Override
    public void run() {
        super.run();
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Log.i("Wait", "[SERVER THREAD] Waiting for a client invocation...");
                Socket socket = serverSocket.accept();
                Log.i("Request", "[SERVER THREAD] A connection request was received from " + socket.getInetAddress() + ":" + socket.getLocalPort());
                CommunicationThread communicationThread = new CommunicationThread(this, socket);
                communicationThread.start();
            }
        } catch (ClientProtocolException clientProtocolException) {
            Log.e("Exception", "[SERVER THREAD] An exception has occurred: " + clientProtocolException.getMessage());
            if (true) {
                clientProtocolException.printStackTrace();
            }
        } catch (IOException ioException) {
            Log.e("Exception", "[SERVER THREAD] An exception has occurred: " + ioException.getMessage());
            if (true) {
                ioException.printStackTrace();
            }
        }

    }

    public void stopThread() {
        interrupt();
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException ioException) {
                Log.e(Constants.TAG, "[SERVER THREAD] An exception has occurred: " + ioException.getMessage());
                if (Constants.DEBUG) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
