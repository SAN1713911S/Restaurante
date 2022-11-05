package Cuenta_nouso;

public interface ICuenta {

	Cuenta retirarDinero(Cuenta cuenta, double monto);
	Cuenta depositarDinero(Cuenta cuenta, double monto);
	double mostrarSaldo(Cuenta cuenta);
}