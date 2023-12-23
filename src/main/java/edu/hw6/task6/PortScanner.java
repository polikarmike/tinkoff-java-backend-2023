package edu.hw6.task6;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;

public class PortScanner {
    private PortScanner() {

    }

    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 49151;
    private static final String FORMAT_STRING = "%-10s%-7s%-8s%-20s";
    private static final Map<Integer, String> KNOWN_PORTS = Map.of(
        53, "Domain Name System",
        135, "EPMAP",
        137, "NetBIOS Name Service",
        138, "NetBIOS Datagram Service",
        139, "NetBIOS Session Service",
        445, "Microsoft-DS (Active Directory)",
        1900, "Simple Service Discovery Protocol (SSDP)",
        3702, "Dynamic Web Services Discovery",
        5353, "Multicast DNS",
        8080, "HTTP proxy server"
    );

    public static void scanPorts(String outputPath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            writeHeader(writer);

            for (int port = MIN_PORT; port <= MAX_PORT; port++) {
                checkAndWritePortStatus(port, writer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeHeader(BufferedWriter writer) throws IOException {
        writeResultToFile(writer, String.format(FORMAT_STRING, "Протокол", "Порт", "Статус", "Сервис"));
    }

    private static void checkAndWritePortStatus(int port, BufferedWriter writer) {
        boolean tcpOccupied = checkTCPPort(port, writer);
        if (!tcpOccupied) {
            checkUDPPort(port, writer);
        }
    }

    private static boolean checkTCPPort(int port, BufferedWriter writer) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return false;
        } catch (IOException e) {
            handlePortOccupied(writer, "TCP", port);
            return true;
        }
    }

    private static void checkUDPPort(int port, BufferedWriter writer) {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            // Empty try block
        } catch (IOException e) {
            handlePortOccupied(writer, "UDP", port);
        }
    }

    private static void handlePortOccupied(BufferedWriter writer, String protocol, int port) {
        String service = getKnownService(port);
        try {
            writeResultToFile(writer, formatResult(protocol, port, "Занят", service));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String formatResult(String protocol, int port, String status, String service) {
        return String.format(FORMAT_STRING, protocol, port, status, service);
    }

    private static String getKnownService(int port) {
        return KNOWN_PORTS.getOrDefault(port, "");
    }

    private static void writeResultToFile(BufferedWriter writer, String result) throws IOException {
        writer.write(result);
        writer.newLine();
    }

}

