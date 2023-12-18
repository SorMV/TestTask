package ru.kazan.express.testtask.calculations.impl;

import org.springframework.stereotype.Service;
import ru.kazan.express.testtask.calculations.interfaces.CalculationService;

import java.util.HashMap;
import java.util.Map;

@Service("recursion")
public class CalculationServiceRecursionImpl implements CalculationService {
    private static Map<Integer, Long> cache = new HashMap<>();

    @Override
    public Long fillFirstNElements(int sequenceNumber) {
        switch (sequenceNumber) {
            case 1:
                return 1L;
            case 2:
                return 2L;
            case 3:
                return 3L;
            default: {
                long first = getOrCalculateElementOfSequence(sequenceNumber-3);
                long second = getOrCalculateElementOfSequence(sequenceNumber-1);
                long current =  first + second;
                cache.put(sequenceNumber, current);
                return current;
            }
        }
    }

    private long getOrCalculateElementOfSequence(int sequenceNumber) {
        if (cache.get(sequenceNumber) == null) {
            Long addNewElement = fillFirstNElements(sequenceNumber);
            cache.put(sequenceNumber, addNewElement);
        }
        return cache.get(sequenceNumber);
    }

    @Override
    public Map<Integer, Long> getPreCountedElements() {
        return Map.copyOf(cache);
    }
}
