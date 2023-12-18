package ru.kazan.express.testtask.calculations.impl;

import org.springframework.stereotype.Service;
import ru.kazan.express.testtask.calculations.interfaces.CalculationService;

import java.util.*;

@Service("cycle")
public class CalculationServiceCycleImpl implements CalculationService {
    private List<Long> sequence = new ArrayList<Long>();

    @Override
    public Long fillFirstNElements(int sequenceNumber) {
        sequence.add(1L);
        sequence.add(2L);
        sequence.add(3L);
        switch (sequenceNumber) {
            case 1:
                return sequence.get(0);
            case 2:
                return sequence.get(1);
            case 3:
                return sequence.get(2);
            default: {
                int size = sequence.size();
                while (size < sequenceNumber) {
                    long elem = sequence.get(size - 3) + sequence.get(size - 1);
                    sequence.add(elem);
                    size++;
                }
                return sequence.get(sequenceNumber - 1);
            }
        }
    }

    @Override
    public Map<Integer, Long> getPreCountedElements() {
        Map<Integer, Long> preCountedElements = new HashMap<>();
        for (int i=0; i<sequence.size(); i++)
            preCountedElements.put(i+1, sequence.get(i));
        return preCountedElements;
    }

    public void showPrecalculatedSequence() {
        for (int i = 0; i < sequence.size(); i++)
            System.out.printf("Element number {%d} value = {%d}%n", i, sequence.get(i));
    }

}
