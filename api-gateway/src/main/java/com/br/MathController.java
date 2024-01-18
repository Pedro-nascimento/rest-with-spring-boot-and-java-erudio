package com.br;

import com.br.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {


    private final AtomicLong count = new AtomicLong();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method= RequestMethod.GET)
    public Double sum(
            @PathVariable(value = "numberOne")  String numberOne,
            @PathVariable(value = "numberTwo")  String numberTwo
            ) throws Exception{

        if(!isNumerico(numberOne) || !isNumerico(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");

                return  convertDouble(numberOne) + convertDouble(numberTwo);
    }

    @RequestMapping(value = "/subtract/{numberOne}/{numberTwo}", method= RequestMethod.GET)
    public Double subtract(
            @PathVariable(value = "numberOne")  String numberOne,
            @PathVariable(value = "numberTwo")  String numberTwo
    ) throws Exception{

        if(!isNumerico(numberOne) || !isNumerico(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");

        return  convertDouble(numberOne) - convertDouble(numberTwo);
    }

    @RequestMapping(value = "/multiply/{numberOne}/{numberTwo}", method= RequestMethod.GET)
    public Double multiply(
            @PathVariable(value = "numberOne")  String numberOne,
            @PathVariable(value = "numberTwo")  String numberTwo
    ) throws Exception{

        if(!isNumerico(numberOne) || !isNumerico(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");

        return  convertDouble(numberOne) * convertDouble(numberTwo);
    }

    @RequestMapping(value = "/divide/{numberOne}/{numberTwo}", method= RequestMethod.GET)
    public Double divide(
            @PathVariable(value = "numberOne")  String numberOne,
            @PathVariable(value = "numberTwo")  String numberTwo
    ) throws Exception{

        if(!isNumerico(numberOne) || !isNumerico(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");

        return  convertDouble(numberOne)/convertDouble(numberTwo);
    }

    @RequestMapping(value = "/mean/{numberOne}/{numberTwo}", method= RequestMethod.GET)
    public Double mean(
            @PathVariable(value = "numberOne")  String numberOne,
            @PathVariable(value = "numberTwo")  String numberTwo
    ) throws Exception{

        if(!isNumerico(numberOne) || !isNumerico(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value");

        return  (convertDouble(numberOne) + convertDouble(numberTwo))/2;
    }

    @RequestMapping(value = "/squareRoot/{number}", method= RequestMethod.GET)
    public Double squareRoot(
            @PathVariable(value = "number")  String numberOne
    ) throws Exception{

        if(!isNumerico(numberOne))
            throw new UnsupportedMathOperationException("Please set a numeric value");

        return  Math.sqrt(convertDouble(numberOne));
    }

    private Double convertDouble(String stringnumero) {
        if(stringnumero == null)
            return  0D;

        String numero = stringnumero.replace(",",".");

        return Double.parseDouble(numero);

    }

    public boolean isNumerico(String stringnumero){
        if(stringnumero == null)
            return false;

        String numero = stringnumero.replace(",",".");

        return numero.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
