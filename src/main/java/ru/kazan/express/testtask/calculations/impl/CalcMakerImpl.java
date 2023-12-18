package ru.kazan.express.testtask.calculations.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kazan.express.testtask.calculations.interfaces.CalcMaker;
import ru.kazan.express.testtask.calculations.interfaces.CalculationService;
import ru.kazan.express.testtask.exceptions.SequenceSizeException;

@Service
public class CalcMakerImpl implements CalcMaker {

    @Value("${calc.elem}")
    private int elem;

    @Value("${calc.mod}")
    private long mod;

    @Autowired
    @Qualifier("recursion")
    private CalculationService calculationServiceRecursionImpl;

    @Override
    public Long getAbsValue() throws SequenceSizeException {
        return calculationServiceRecursionImpl.calculateSum(elem)%mod;
    }
}
