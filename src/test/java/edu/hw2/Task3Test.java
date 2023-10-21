package edu.hw2;

import edu.hw2.Task3.ConnectionException;
import edu.hw2.Task3.ConnectionManager;
import edu.hw2.Task3.DefaultConnectionManager;
import edu.hw2.Task3.FaultyConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


public class Task3Test {
    private ConnectionManager connectionManager;

    @Test
    @DisplayName("Тест на обновление пакетов должен выполняться без исключений (большое количество попыток, маленькая вероятность выпадения ошибки)")
    public void testUpdatePackages() throws Exception {
        // Given
        connectionManager = new DefaultConnectionManager();
        PopularCommandExecutor executor = new PopularCommandExecutor(connectionManager, 100);

        // When
        executor.updatePackages();

        // Then: Никаких исключений не должно возникать.
    }

    @Test
    @DisplayName("Тест на выполнение команды должен выполняться без исключений (большое количество попыток, маленькая вероятность выпадения ошибки)")
    public void testTryExecute_Successful() throws Exception {
        // Given
        connectionManager = new DefaultConnectionManager();
        PopularCommandExecutor executor = new PopularCommandExecutor(connectionManager, 100);
        String command = "someCommand";

        // When
        executor.tryExecute(command);

        // Then: Никаких исключений не должно возникать.
    }

    @Test
    @DisplayName("Тест на выброс исключения (маленькое количество попыток, использование faultyConnectionManager)")
    public void testTryExecute_Failure() throws Exception {
        // Given
        ConnectionManager faultyConnectionManager = new FaultyConnectionManager();
        PopularCommandExecutor executor = new PopularCommandExecutor(faultyConnectionManager, 1);
        String command = "someCommand";
        // When and Then: Должно быть выброшено исключение
        try {
            executor.tryExecute(command);
            assert false : "Ожидалось возникновение ConnectionException, но исключение не было вызвано.";
        } catch (ConnectionException ignored) {
        }

    }
}
