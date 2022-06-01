package assignment;

abstract class calculation_two {
    abstract double operation(double num1,double num2);
}

class add extends calculation_two{
    double operation(double num1,double num2){
        return num1 + num2;
    }
}

class multiply extends calculation_two{
    double operation(double num1,double num2){
        return num1 * num2;
    }
}

class subtract extends calculation_two{
    double operation(double num1,double num2){
        return num1 * num2;
    }
}

class divide extends calculation_two{
    double operation(double num1,double num2){
        return num1 / num2;
    }
}

class modular extends calculation_two{
    double operation(double num1,double num2){
        return num1 % num2;
    }
}

class pow extends calculation_two{
    double operation(double num1,double num2){
        return Math.pow(num1, num2);
    }
}

class pow_10 extends calculation_two{
    double operation(double num1,double num2){
        return num1*Math.pow(10, num2);
    }
}

abstract class factory2{
    abstract calculation_two calculation();
}

class add_factory extends factory2{
    @Override
    calculation_two calculation() {
        return new add();
    }
}

class subtract_factory extends factory2{
    @Override
    calculation_two calculation() {
        return new subtract();
    }
}

class multiply_factory extends factory2{
    @Override
    calculation_two calculation() {
        return new multiply();
    }
}

class divide_factory extends factory2{
    @Override
    calculation_two calculation() {
        return new divide();
    }
}

class modular_factory extends factory2{
    @Override
    calculation_two calculation() {
        return new modular();
    }
}

class pow_factory extends factory2{
    @Override
    calculation_two calculation() {
        return new pow();
    }
}

class pow_10_factory extends factory2{
    @Override
    calculation_two calculation() {
        return new pow_10();
    }
}







