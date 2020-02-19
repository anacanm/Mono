//Anacan Mangelsdorf
//PPL 2
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.io.File;

public class Mono
{
  
  static ArrayList<String> tokens = new ArrayList<String>(); 
  static ArrayList<String> types = new ArrayList<String>();
  static ArrayList<String> variables = new ArrayList<String>();
  static ArrayList<Double> numbers = new ArrayList<Double>();
  static ArrayList<Double> answers = new ArrayList<Double>();
  
  public static void tokenizer()throws FileNotFoundException{
    Scanner input = new Scanner(new File("burner.mono"));

    while(input.hasNext()){
      String s = input.next();
      
      if (s.charAt(0) == '"') {
        while (s.charAt(s.length() -1) != '"') {
          s += " " + input.next();  }}
      
      // System.out.println(s);
      tokens.add(s);  }}
  
  
  
  public static void parser()
  {
    Scanner console = new Scanner(System.in);
    int i = 0;
    int indexMath = 0;
    double solution = 0.0;
    String display = "";
    boolean comparator = false;
    while(i<tokens.size()){
      //System.out.println(tokens.get(i));
      
      if(tokens.get(i).equals("disp")){
        display = tokens.get(i+1).substring(1,tokens.get(i+1).length()-1);
        System.out.print(display);}                                         //simple console output
      else if(tokens.get(i).equals("dispLine")){
        display = tokens.get(i+1).substring(1,tokens.get(i+1).length()-1);
        System.out.println(display);}
      else if(tokens.get(i).equals("dispVal")){
        if(variables.contains(tokens.get(i+1))){
          int a = variables.indexOf(tokens.get(i+1));
          System.out.print(numbers.get(a)+" ");
        }}
      else if(tokens.get(i).equals("dispValLine")){
        if(variables.contains(tokens.get(i+1))){
          int a = variables.indexOf(tokens.get(i+1));
          System.out.println(numbers.get(a)+" ");
        }}
      
      
      
      
      else if(tokens.get(i).equals("Number")){
        types.add(tokens.get(i));}
      
      
      
      else if(tokens.get(i).equals("is")){                                                             //declaration
        if(!(variables.contains(tokens.get(i-1))))
        {
          variables.add(tokens.get(i-1));
        }
        
        int indexVar = variables.indexOf(tokens.get(i-1));
        
        if(tokens.get(i+1).equals("ans")&&answers.size()>0){
          if(indexVar<numbers.size())
          {
            numbers.set(indexVar,answers.get(answers.size()-1));
          }
          
          else
            numbers.add(answers.get(answers.size()-1));}
        
        
         else if(tokens.get(i+1).equals("key")){
           String keyin = console.next();
            tokens.set(i+1,keyin);
            Double d = cast(tokens.get(i+1));
            if(indexVar<numbers.size())
          {
            numbers.set(indexVar,d);
          }
          
          else
            numbers.add(d);}
            
        
        else{
          try{  
            indexMath = i-1;
            int intX = Integer.parseInt(tokens.get(i+1));
            double doubleX = (double)(intX); 
            String conv = String.valueOf(doubleX);
            tokens.set(i+1, conv);
            if(indexVar<numbers.size())
              numbers.set(indexVar,doubleX);
            else
              numbers.add(doubleX);}
          
          catch (NumberFormatException e) {
            double doubleY = Double.parseDouble(tokens.get(i+1));
            
            if(indexVar<numbers.size())
              numbers.set(indexVar, doubleY);
            else 
              numbers.add(doubleY);}} }
      
      
      
      else if(tokens.get(i).equals("plus")&&variables.contains(tokens.get(i-1))){                                   //addition
        int a = variables.indexOf(tokens.get(i-1));
        int b = variables.indexOf(tokens.get(i+1));
        
        solution = numbers.get(a) + numbers.get(b);
        answers.add(solution);
        //System.out.println(solution);
      }
      
      else if(tokens.get(i).equals("plus")){
        double a = cast(tokens.get(i-1)); 
        double b = cast(tokens.get(i+1));
        solution = a+b;
        answers.add(solution);
        //System.out.println(solution);
      }
      
      
      
      else if(tokens.get(i).equals("minus")&&variables.contains(tokens.get(i-1))){                                   //subtraction
        int a = variables.indexOf(tokens.get(i-1));
        int b = variables.indexOf(tokens.get(i+1));
        
        solution = numbers.get(a) - numbers.get(b);
        answers.add(solution);
       // System.out.println(solution);
      }
      
      else if(tokens.get(i).equals("minus")){
        double a = cast(tokens.get(i-1)); 
        double b = cast(tokens.get(i+1));
        solution = a-b;
        answers.add(solution);
        //System.out.println(solution);
      }
      
      
      
      else if(tokens.get(i).equals("mult")&&variables.contains(tokens.get(i-1))){                                   //multiplication
        int a = variables.indexOf(tokens.get(i-1));
        int b = variables.indexOf(tokens.get(i+1));
        
        solution = numbers.get(a) * numbers.get(b);
        answers.add(solution);
       // System.out.println(solution);
      }
      
      else if(tokens.get(i).equals("mult")){
        double a = cast(tokens.get(i-1)); 
        double b = cast(tokens.get(i+1));
        solution = a*b;
        answers.add(solution);
       // System.out.println(solution);
      }
      
      
      else if(tokens.get(i).equals("div")&&variables.contains(tokens.get(i-1))&&variables.contains(tokens.get(i+1))){       //division
        int a = variables.indexOf(tokens.get(i-1));
        int b = variables.indexOf(tokens.get(i+1));
        
        solution = numbers.get(a) / numbers.get(b);
        answers.add(solution);
       // System.out.println(solution);
      }
      
      else if(tokens.get(i).equals("div")&&variables.contains(tokens.get(i-1))){
        int a = variables.indexOf(tokens.get(i-1));
        double b = cast(tokens.get(i+1));
        solution = numbers.get(a)/b;
        answers.add(solution);
       // System.out.println(solution);
      }
      
      else if(tokens.get(i).equals("div")&&variables.contains(tokens.get(i+1))){
        double a = cast(tokens.get(i-1));
        int b = variables.indexOf(tokens.get(i+1));
        solution = a/numbers.get(b);
        answers.add(solution);
       // System.out.println(solution);
      }
      
      else if(tokens.get(i).equals("div")){
        double a = cast(tokens.get(i-1)); 
        double b = cast(tokens.get(i+1));
        solution = a/b;
        answers.add(solution);
      //  System.out.println(solution);
      }
      
      else if(tokens.get(i).equals("divI")&&variables.contains(tokens.get(i-1))&&variables.contains(tokens.get(i+1))){                                   //Integral division
        int a = variables.indexOf(tokens.get(i-1));
        int b = variables.indexOf(tokens.get(i+1));
        int integralSolution = 0;
        integralSolution = (int)(numbers.get(a) / numbers.get(b));
        double burn = (double)integralSolution;
        answers.add(burn);
        
      //  System.out.println(integralSolution);
      }
      
      else if(tokens.get(i).equals("divI")&&variables.contains(tokens.get(i-1))){
        int a = variables.indexOf(tokens.get(i-1));
        double b = cast(tokens.get(i+1));
        int integralSolution = 0;
        integralSolution = (int)(numbers.get(a)/b);
        double burn = (double)integralSolution;
        answers.add(burn);
       // System.out.println(integralSolution);
      }
      
      else if(tokens.get(i).equals("divI")&&variables.contains(tokens.get(i+1))){
        double a = cast(tokens.get(i-1));
        int b = variables.indexOf(tokens.get(i+1));
        int integralSolution = 0;
        integralSolution = (int)(a/numbers.get(b));
        double burn = (double)integralSolution;
        answers.add(burn);
       // System.out.println(integralSolution);
      }
      
      else if(tokens.get(i).equals("divI"))
      {
        double a = cast(tokens.get(i-1));
        double b = cast(tokens.get(i-1));
        int integralSolution = 0;
        integralSolution = (int)(a/b);
        double burn = (double)integralSolution;
        answers.add(burn);
       // System.out.println(integralSolution);
      }
      
      else if(tokens.get(i).equals("mod")&&variables.contains(tokens.get(i-1))&&variables.contains(tokens.get(i+1))){                                   //modular
        int a = variables.indexOf(tokens.get(i-1));
        int b = variables.indexOf(tokens.get(i+1));
        
        solution = numbers.get(a) % numbers.get(b);
        answers.add(solution);
     //   System.out.println(solution);
      }
      
      else if(tokens.get(i).equals("mod")&&variables.contains(tokens.get(i-1))){
        int a = variables.indexOf(tokens.get(i-1));
        double b = cast(tokens.get(i+1));
        solution = numbers.get(a)%b;
        answers.add(solution);
       // System.out.println(solution);
      }
      
      else if(tokens.get(i).equals("mod")&&variables.contains(tokens.get(i+1))){
        double a = cast(tokens.get(i-1));
        int b = variables.indexOf(tokens.get(i+1));
        solution = a%numbers.get(b);
        answers.add(solution);
       // System.out.println(solution);
      }
      
      else if(tokens.get(i).equals("mod")){
        double a = cast(tokens.get(i-1)); 
        double b = cast(tokens.get(i+1));
        solution = a%b;
        answers.add(solution);
      //  System.out.println(solution);
      }
      
      
      
      
      
      
      
      else if(tokens.get(i).equals("con")){                                        //conditional
        
        if (tokens.get(i+2).equals("equals")){
          comparator = equal(i+2);}
        else if(tokens.get(i+2).equals("notEquals")){
          comparator = notEqual(i+2);}
        else if(tokens.get(i+2).equals("lessThan")){
          comparator = lessThan(i+2);}
        else if(tokens.get(i+2).equals("greaterThan")){
          comparator = greaterThan(i+2);}
        else if(tokens.get(i+2).equals("lessEquals")){
          comparator = lessEquals(i+2);}
        else if(tokens.get(i+2).equals("greaterEquals")){
          comparator = greaterEquals(i+2);}
        // System.out.println(comparator);
        
        
        if(!comparator)
        {
          
          while(i<tokens.size()){
            i++;
            if(tokens.get(i).equals(">")){
              
              break; }}}
        
      }
      
      
      i++;
    }
    
    
    
    
  }
  
  
  
  
  public static boolean equal(int i){                                             //equals
    if(variables.contains(tokens.get(i-1))&&variables.contains(tokens.get(i+1))){
      int a = variables.indexOf(tokens.get(i-1));
      int b = variables.indexOf(tokens.get(i+1));
      
      double x = numbers.get(a); 
      double y = numbers.get(b);
      
      if(x==y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    else if(variables.contains(tokens.get(i-1)))
    {
      int a = variables.indexOf(tokens.get(i-1));
      double x = numbers.get(a); 
      
      double y = cast(tokens.get(i+1));
      if(x==y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    
    else if(variables.contains(tokens.get(i+1))){
      int b = variables.indexOf(tokens.get(i+1));
      double y = numbers.get(b); 
      
      double x = cast(tokens.get(i-1));
      if(x==y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    
    else
    {
      
      double x = cast(tokens.get(i-1)); 
      double y = cast(tokens.get(i+1));
      if(x == y){
        // System.out.println("true");
        return true;
      }
      else
        // System.out.println("false");
        return false;}
    
  }
  
  
  
  
  public static boolean notEqual(int i){                                             //notEquals
    if(variables.contains(tokens.get(i-1))&&variables.contains(tokens.get(i+1))){
      int a = variables.indexOf(tokens.get(i-1));
      int b = variables.indexOf(tokens.get(i+1));
      
      double x = numbers.get(a); 
      double y = numbers.get(b);
      
      if(x!=y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    else if(variables.contains(tokens.get(i-1)))
    {
      int a = variables.indexOf(tokens.get(i-1));
      double x = numbers.get(a); 
      
      double y = cast(tokens.get(i+1));
      if(x!=y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    
    else if(variables.contains(tokens.get(i+1))){
      int b = variables.indexOf(tokens.get(i+1));
      double y = numbers.get(b); 
      
      double x = cast(tokens.get(i-1));
      if(x!=y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    
    else
    {
      
      double x = cast(tokens.get(i-1)); 
      double y = cast(tokens.get(i+1));
      if(x != y){
        // System.out.println("true");
        return true;
      }
      else
        // System.out.println("false");
        return false;}
    
  }
  
  
  public static boolean lessThan(int i){                                             //lessThan
    if(variables.contains(tokens.get(i-1))&&variables.contains(tokens.get(i+1))){
      int a = variables.indexOf(tokens.get(i-1));
      int b = variables.indexOf(tokens.get(i+1));
      
      double x = numbers.get(a); 
      double y = numbers.get(b);
      
      if(x<y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    else if(variables.contains(tokens.get(i-1)))
    {
      int a = variables.indexOf(tokens.get(i-1));
      double x = numbers.get(a); 
      
      double y = cast(tokens.get(i+1));
      if(x<y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    
    else if(variables.contains(tokens.get(i+1))){
      int b = variables.indexOf(tokens.get(i+1));
      double y = numbers.get(b); 
      
      double x = cast(tokens.get(i-1));
      if(x<y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    
    else
    {
      
      double x = cast(tokens.get(i-1)); 
      double y = cast(tokens.get(i+1));
      if(x < y){
        // System.out.println("true");
        return true;
      }
      else
        // System.out.println("false");
        return false;}
    
  }
  
  
  public static boolean greaterThan(int i){                                             //greaterThan
    if(variables.contains(tokens.get(i-1))&&variables.contains(tokens.get(i+1))){
      int a = variables.indexOf(tokens.get(i-1));
      int b = variables.indexOf(tokens.get(i+1));
      
      double x = numbers.get(a); 
      double y = numbers.get(b);
      
      if(x>y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    else if(variables.contains(tokens.get(i-1)))
    {
      int a = variables.indexOf(tokens.get(i-1));
      double x = numbers.get(a); 
      
      double y = cast(tokens.get(i+1));
      if(x>y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    
    else if(variables.contains(tokens.get(i+1))){
      int b = variables.indexOf(tokens.get(i+1));
      double y = numbers.get(b); 
      
      double x = cast(tokens.get(i-1));
      if(x>y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    
    else
    {
      
      double x = cast(tokens.get(i-1)); 
      double y = cast(tokens.get(i+1));
      if(x > y){
        // System.out.println("true");
        return true;
      }
      else
        // System.out.println("false");
        return false;}
    
  }
  
  
  
  public static boolean lessEquals(int i){                                             //lessEquals
    if(variables.contains(tokens.get(i-1))&&variables.contains(tokens.get(i+1))){
      int a = variables.indexOf(tokens.get(i-1));
      int b = variables.indexOf(tokens.get(i+1));
      
      double x = numbers.get(a); 
      double y = numbers.get(b);
      
      if(x<=y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    else if(variables.contains(tokens.get(i-1)))
    {
      int a = variables.indexOf(tokens.get(i-1));
      double x = numbers.get(a); 
      
      double y = cast(tokens.get(i+1));
      if(x<=y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    
    else if(variables.contains(tokens.get(i+1))){
      int b = variables.indexOf(tokens.get(i+1));
      double y = numbers.get(b); 
      
      double x = cast(tokens.get(i-1));
      if(x<=y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    
    else{
      
      double x = cast(tokens.get(i-1)); 
      double y = cast(tokens.get(i+1));
      if(x <= y){
        // System.out.println("true");
        return true;
      }
      else
        // System.out.println("false");
        return false;}
    
  }
  
  
  public static boolean greaterEquals(int i){                                             //greaterEquals
    if(variables.contains(tokens.get(i-1))&&variables.contains(tokens.get(i+1))){
      int a = variables.indexOf(tokens.get(i-1));
      int b = variables.indexOf(tokens.get(i+1));
      
      double x = numbers.get(a); 
      double y = numbers.get(b);
      
      if(x>=y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    else if(variables.contains(tokens.get(i-1)))
    {
      int a = variables.indexOf(tokens.get(i-1));
      double x = numbers.get(a); 
      
      double y = cast(tokens.get(i+1));
      if(x>=y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    
    else if(variables.contains(tokens.get(i+1))){
      int b = variables.indexOf(tokens.get(i+1));
      double y = numbers.get(b); 
      
      double x = cast(tokens.get(i-1));
      if(x>=y){
        // System.out.println("true");
        return true;
      }
      else
        //System.out.println("false");
        return false;}
    
    else
    {
      
      double x = cast(tokens.get(i-1)); 
      double y = cast(tokens.get(i+1));
      if(x >= y){
        // System.out.println("true");
        return true;
      }
      else
        // System.out.println("false");
        return false;}
    
  }
  
  
  
  public static double cast(String casting)
  {
    try{
      int intX = Integer.parseInt(casting);
      double doubleX = (double)(intX);
      return doubleX;}
    catch (NumberFormatException e) {
      double doubleY = Double.parseDouble(casting);
      return doubleY;}
    
  }
  
  
  public static void main(String[] args) throws IOException
  {
    tokenizer();
    parser();
    
    for(int i = 0; i<answers.size();i++)
    {
       
      // System.out.println(variables.get(i));
      // System.out.println(numbers.get(i));
    }
  
  }
  
}


