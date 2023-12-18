package ru.kazan.express.testtask.calculations.interfaces;

import ru.kazan.express.testtask.exceptions.SequenceSizeException;

import java.util.Map;

public interface CalculationService {

    public Long fillFirstNElements(int n);

    /*здесь, чистов теории стоит предусмотреть ситуацию переполнения значения лонга
        в случае, если требуется взять сильно большую сумму элементов, однако,
        в постановке об этом ни слова, поэтому, подразумевается, что если алгоритм формирования элементов
        или необходимая сумма будет другой, то реализующий новую имплементацию программист предусмотрит эти моменты
    */
    public default Long calculateSum(int sumElements) throws SequenceSizeException {
        if (sumElements <= 0)
            throw new SequenceSizeException("Sequence number must be greater than 0!");
        fillFirstNElements(sumElements);
        Map<Integer, Long> preCounted = getPreCountedElements();
        long result = 0L;
        for (int i = 1; i<=sumElements; i++) {
            result += preCounted.get(i);
        }
        return result;
    }

    public Map<Integer, Long> getPreCountedElements();

//getPreCountedElements    public default Long getAbsValue(int elem, long mod) throws SequenceSizeException {
//        return calculateSum(elem)%mod;
//    }


}
