package ru.kazan.express.testtask.calculations.interfaces;

import ru.kazan.express.testtask.exceptions.SequenceSizeException;

public interface CalcMaker {

    public Long getAbsValue() throws SequenceSizeException;

}
