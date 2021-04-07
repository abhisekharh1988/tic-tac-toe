package providers;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CachedDataProviderTest {

    @Test
    public void shouldCreateOnlyOneInstanceOfCacheDataProvider() {
        CachedDataProvider cachedDataProvider1 = CachedDataProvider.getInstance();
        CachedDataProvider cachedDataProvider2 = CachedDataProvider.getInstance();

        assertEquals(cachedDataProvider1, cachedDataProvider2);
    }

    @Test
    public void shouldReturnWinningConditions() {
        List<List<Integer>> winningConditions = CachedDataProvider.getInstance().getWinningConditions();

        assertEquals(8, winningConditions.size());
    }
}