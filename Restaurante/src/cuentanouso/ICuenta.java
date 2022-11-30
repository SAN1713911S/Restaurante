package cuentanouso;

public interface ICuenta {

	Cuenta recargarCuenta(Cuenta cuenta, double monto);
	Cuenta pagarPedido(Cuenta cuenta, double monto);
	double mostrarSaldo(Cuenta cuenta);
	void crearCuenta();
}