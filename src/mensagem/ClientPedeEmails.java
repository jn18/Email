package mensagem;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClientPedeEmails implements Runnable {

	private ObjectOutputStream oos;
	private String email;

	public ClientPedeEmails(ObjectOutputStream oos, String email) {
		this.oos = oos;
		this.email = email;
	}

	@Override
	public void run() {
		
		try {
			oos.writeObject(email);//ENVIO P/ SERVIDOR
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}