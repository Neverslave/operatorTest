import org.jetbrains.annotations.Contract;
import java.util.Stack;
public class EvaluateExpression {
    public static void main(String[] args){
    if(args.length!=1){
        System.out.println("Usage:Java EvaluateExpression");
        System.exit(1);
    }
    try {
        System.out.println(evaluateExpression(args[0]));
    }catch (Exception ex){
        System.out.println("wrong expression:"+args[0]);
    }

    }
    //计算表达式
    public static int evaluateExpression(String expression){
        //储存操作数的栈
        Stack<Integer> operandStack = new Stack<>();
        //储存操作符的栈
        Stack<Character> operatorStack = new Stack<>();
        //操作符区分 结果为：55  +  33  * 5
        expression = insertBlanks(expression);
        //存放操作符和操作数的数组
        String []tokens = expression.split(" ");
        //1.首先遍历数组
        for (String token:tokens
             ) {
            if(token.length()==0){
                continue;
            }else if(token.charAt(0)== '+'||token.charAt(0)== '-'){
                while (!operatorStack.isEmpty()&&
                        (operatorStack.peek()=='+'||operatorStack.peek()=='-'||
                                operatorStack.peek()=='*'||operatorStack.peek()=='/')){
                    processAnOperator(operandStack,operatorStack);
                }
                operatorStack.push(token.charAt(0));
            }else if(token.charAt(0)=='*'||token.charAt(0)=='/'){
                while(!operatorStack.isEmpty()&&(operatorStack.peek()=='*'||
                operatorStack.peek()=='/')){
                    processAnOperator(operandStack,operatorStack);
                }
                operatorStack.push(token.charAt(0));
            }else if(token.trim().charAt(0)=='('){
                operatorStack.push('(');
            }else if(token.trim().charAt(0)==')'){
                while (operatorStack.peek()!='('){
                    processAnOperator(operandStack,operatorStack);
                }
                operatorStack.pop();
            }else {
                operandStack.push(new Integer(token));
            }

        }
        while(!operatorStack.isEmpty()){
            processAnOperator(operandStack,operatorStack);
        }
        return operandStack.pop();

    }
    //取出数字和操作符 用空格区分
    @Contract(pure = true)
    public static  String insertBlanks(String s){
        String result ="";
        for (int i = 0; i <s.length() ; i++) {
            if(s.charAt(i)=='('||s.charAt(i)==')'||s.charAt(i)=='+'||
                    s.charAt(i)=='-'||s.charAt(i)=='*'||s.charAt(i)=='/'){
                result+=" "+s.charAt(i)+" ";
            }else {
                result +=s.charAt(i);
            }

        }

        return  result;
    }
    //计算结果
    public static void processAnOperator(Stack<Integer> operandStack,Stack<Character> operatorStack){
        char op =operatorStack.pop();
        int op1 =operandStack.pop();
        int op2 =operandStack.pop();
        if(op =='+'){
            operandStack.push(op2+op1);
        }else if(op=='-'){
            operandStack.push(op2-op1);
        }else if(op == '*'){
            operandStack.push(op2*op1);
        }else if(op =='/'){
            operandStack.push(op2/op1);
        }
    }

}
