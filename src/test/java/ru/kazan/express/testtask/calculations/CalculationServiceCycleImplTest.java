package ru.kazan.express.testtask.calculations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kazan.express.testtask.calculations.interfaces.CalculationService;
import ru.kazan.express.testtask.exceptions.SequenceSizeException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CalculationServiceCycleImplTest {

    @Autowired
    @Qualifier("cycle")
    private CalculationService calculationService;

    @Test
    void fillFirstNElemTest() {
        int sequenceNumber = 4;
        calculationService.fillFirstNElements(sequenceNumber);
        Map<Integer, Long> preCountedElements = calculationService.getPreCountedElements();
        assertEquals(preCountedElements.size(), 4);
        assertEquals(preCountedElements.get(4), 4);

    }

    @Test
    void calculateSumTest() throws SequenceSizeException {
        int sequenceNumber = 4;
        Long sum = calculationService.calculateSum(sequenceNumber);
        Map<Integer, Long> preCountedElements = calculationService.getPreCountedElements();
        assertEquals(preCountedElements.size(), 4);
        assertEquals(preCountedElements.get(4), 4);
        assertEquals(sum, 10);

    }

    @Test
    void calculateSumNegativeTest() throws SequenceSizeException {
        int sequenceNumber = 0;
        assertThrows(SequenceSizeException.class, ()->calculationService.calculateSum(sequenceNumber));
    }
}
