import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Arrays;
import java.util.Scanner;


public class Process {
     int firstNumber;
     int secondNumber;
     int result;
     String firstExprElement;
     String secondExprElement;
     String operator;
     String expressionToSolve;
     boolean isArabic ;




    public String getFirstExprElement() {
        return firstExprElement;
    }

    public void setFirstExprElement(String firstExprElement) {
        this.firstExprElement = firstExprElement;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }



    public void processing(){


        Scanner in = new Scanner(System.in);
        String inExpression = in.nextLine();
        System.out.println(inExpression.toUpperCase());

        in.close();

        String[] expressionToShow = inExpression.split("[+*/-]");
        String[] expressionToOperator = inExpression.split("[^+*/-]");


        System.out.println("expressionTo = " + Arrays.toString(expressionToOperator));

        System.out.println("expressionToArr = " + Arrays.toString(expressionToShow));
         firstExprElement = expressionToShow[0];
         secondExprElement = expressionToShow[1];
         operator =  expressionToOperator[(expressionToOperator.length)-1];



        if (firstExprElement.matches(".*\\d+.*") && secondExprElement.matches(".*\\d+.*")) {
            isArabic = true;


        firstNumber = Integer.parseInt(firstExprElement);
        secondNumber = Integer.parseInt(secondExprElement);


     }
        else {
        firstNumber = RomanNumerals.romanToArabic(firstExprElement);
        secondNumber = RomanNumerals.romanToArabic(secondExprElement);

    }
        solveExpression();



    }

    public void solveExpression(){

        if ((firstNumber  <0 || firstNumber >10) || (secondNumber <0 || secondNumber > 10)){
            throw new IllegalArgumentException(" out of range");
        }
        expressionToSolve = (firstNumber + operator + secondNumber);
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        try {

            result = (int)engine.eval(expressionToSolve);
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        if (isArabic){
            System.out.println(result);

        }
        else {
            System.out.println( RomanNumerals.arabicToRoman(result));


        }

    }


}
