import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
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


    public void processing(){

        Scanner in = new Scanner(System.in);
        System.out.println("Введите выражение которое хотите решить или наберите 'exit' для выхода из программы");

        String inExpression ;

        while(in.hasNextLine()) {

            try {
                inExpression = in.nextLine();
                if (inExpression.equals("exit")) break;

                System.out.println(inExpression.toUpperCase());

                String[] expressionToShow = inExpression.split("[+*/-]");
                String[] expressionToOperator = inExpression.split("[^+*/-]");

                firstExprElement = expressionToShow[0];
                secondExprElement = expressionToShow[1];
                operator = expressionToOperator[(expressionToOperator.length) - 1];


                if (firstExprElement.matches(".*\\d+.*") && secondExprElement.matches(".*\\d+.*")) {

                    isArabic = true;
                    firstNumber = Integer.parseInt(firstExprElement);
                    secondNumber = Integer.parseInt(secondExprElement);


                } else {

                    firstNumber = RomanNumerals.romanToArabic(firstExprElement);
                    secondNumber = RomanNumerals.romanToArabic(secondExprElement);

                }
                solveExpression();

            } catch (Exception exception) {
                System.out.println(exception);

                break;
            }

        }
        in.close();
    }

    public void solveExpression(){

        if ((firstNumber  <0 || firstNumber >10) || (secondNumber <0 || secondNumber > 10)){
            throw new IllegalArgumentException(" Калькулятор принимает на вход числа от 1 до 10 включительно");
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
            System.out.println(RomanNumerals.arabicToRoman(result));

        }
    }
}
