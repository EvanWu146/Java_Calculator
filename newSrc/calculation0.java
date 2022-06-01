package assignment;

abstract class calculation_one{
    abstract double operation(double num1);
}

class log_e extends calculation_one{
    @Override
    double operation(double num1) {
        return Math.log(num1);
    }
}

class sqrt extends calculation_one{
    @Override
    double operation(double num1) {
        return Math.sqrt(num1);
    }
}

class reciprocal extends calculation_one{
    @Override
    double operation(double num1) {
        return 1/num1;
    }
}

class sine extends calculation_one{
    @Override
    double operation(double num1) {
        return Math.sin(num1);
    }
}

class cosine extends calculation_one{
    @Override
    double operation(double num1) {
        return Math.cos(num1);
    }
}

class tangent extends calculation_one{
    @Override
    double operation(double num1) {
        return Math.tan(num1);
    }
}

abstract class factory1{
    abstract calculation_one calutation();
}

class log_e_factory extends factory1{
    @Override
    calculation_one calutation() {
        return new log_e();
    }
}

class sqrt_factory extends factory1{
    @Override
    calculation_one calutation() {
        return new sqrt();
    }
}

class reciprocal_factory extends factory1{
    @Override
     calculation_one calutation() {
        return new reciprocal();
    }
}

class sine_factory extends factory1{
    @Override
    calculation_one calutation() {
        return new sine();
    }
}

class cosine_factory extends factory1{
    @Override
    calculation_one calutation() {
        return new cosine();
    }
}

class tangent_factory extends factory1{
    @Override
    calculation_one calutation() {
        return new tangent();
    }
}