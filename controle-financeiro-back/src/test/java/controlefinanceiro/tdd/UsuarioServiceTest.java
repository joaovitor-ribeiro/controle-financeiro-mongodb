package controlefinanceiro.tdd;

public class UsuarioServiceTest {
	
//	IniciaValidatorsUsuario validator = new IniciaValidatorsUsuario();
//	
//	@Test
//	public void cadastroUsuarioSemParametroNome() {
//		try {
//			Usuario usuario = new Usuario();
//			validator.inicia(usuario);
//			fail("O nome é de preenchimento obrigatório e não foi preenchido.");
//		} catch (Exception e) {
//			assertEquals("O campo nome é de preenchimento obrigatório!", e.getMessage());
//		}
//	}
//	
//	@Test
//	public void cadastroUsuarioParametroNomeVazio() {
//		try {
//			Usuario usuario = new Usuario();
//			usuario.setNome("");
//			validator.inicia(usuario);
//			fail("O nome é de preenchimento obrigatório e não foi preenchido.");
//		} catch (Exception e) {
//			assertEquals("O campo nome é de preenchimento obrigatório!", e.getMessage());
//		}
//	}
//	
//	@Test
//	public void cadastroUsuarioValidaTamanhoMinimoNome() {
//		try {
//			Usuario usuario = new Usuario();
//			usuario.setNome("El");
//			usuario.setCpf("85572991090");
//			usuario.setEmail("elza@gmail.com");
//			usuario.setSenha("123456");
//			validator.inicia(usuario);
//			fail("O nome deve conter no mínimo 3 caracteres");
//		} catch (Exception e) {
//			assertEquals("O campo nome não pode ter menos do que 3 caracteres!", e.getMessage());
//		}
//	}
//	
//	@Test
//	public void cadastroUsuarioValidaTamanhoMaximoNome() {
//		 try {
//			Usuario usuario = new Usuario();
//			usuario.setNome("Elza Soares Barboza dos Santos Silva e Silva Andrade de Drumond");
//			usuario.setCpf("85572991090");
//			usuario.setEmail("elza@gmail.com");
//			usuario.setSenha("123456");
//			validator.inicia(usuario);
//			fail("O nome deve conter no máximo 50 caracteres");
//		} catch (Exception e) {
//			assertEquals("O campo nome não pode ter mais do que 50 caracteres!", e.getMessage());
//		}
//	}
//	
//	@Test
//	public void cadastroUsuarioSemParametroCPF() {
//		try {
//			Usuario usuario = new Usuario();
//			usuario.setNome("Elza Soares");
//			usuario.setEmail("elza@gmail.com");
//			usuario.setSenha("123456");
//			validator.inicia(usuario);
//			fail("O CPF é de preenchimento obrigatório!");
//		} catch (Exception e) {
//			assertEquals("O campo CPF é de preenchimento obrigatório!", e.getMessage());
//		}
//	}
//	
//	@Test
//	public void cadastroUsuarioParametroCPFVazio() {
//		try {
//			Usuario usuario = new Usuario();
//			usuario.setNome("Elza Soares");
//			usuario.setCpf("");
//			validator.inicia(usuario);
//			fail("O CPF é de preenchimento obrigatório!");
//		} catch (Exception e) {
//			assertEquals("O campo CPF é de preenchimento obrigatório!", e.getMessage());
//		}
//	}
//	
//	@Test
//	public void cadastroUsuarioSemParametroEmail() {
//		try {
//			Usuario usuario = new Usuario();
//			usuario.setNome("Elza Soares");
//			usuario.setCpf("85572991090");
//			usuario.setSenha("123456");
//			validator.inicia(usuario);
//			fail("O e-mail é de preenchimento obrigatório!");
//		} catch (Exception e) {
//			assertEquals("O campo email é de preenchimento obrigatório!", e.getMessage());
//		}
//	}
//	
//	@Test
//	public void cadastroUsuarioParametroEmailVazio() {
//		try {
//			Usuario usuario = new Usuario();
//			usuario.setNome("Elza Soares");
//			usuario.setCpf("85572991090");
//			usuario.setSenha("123456");
//			usuario.setEmail("");
//			validator.inicia(usuario);
//			fail("O e-mail é de preenchimento obrigatório!");
//		} catch (Exception e) {
//			assertEquals("O campo email é de preenchimento obrigatório!", e.getMessage());
//		}
//	}
//	
//	@Test
//	public void cadastroUsuarioValidaTamanhoMaximoEmail() {
//		try {
//			Usuario usuario = new Usuario();
//			usuario.setNome("Elza Soares");
//			usuario.setCpf("85572991090");
//			usuario.setEmail("elzasoaresbarbozasantosilvasilvaandradedrumond@gmail.com");
//			usuario.setSenha("123456");
//			validator.inicia(usuario);
//			fail("O e-mail não pode ter mais de 50 caracteres");
//		} catch (Exception e) {
//			assertEquals("O email não pode ter mais do que 50 caracteres!", e.getMessage());
//		}
//	}
//	
//	@Test
//	public void cadastroUsuarioInformandoEmailInvalido() {
//		try {
//			Usuario usuario = new Usuario();
//			usuario.setNome("Elza Soares");
//			usuario.setCpf("85572991090");
//			usuario.setSenha("123456");
//			usuario.setEmail("elzasoaresbarbozasantosilvasilvaandradedrumond");
//			validator.inicia(usuario);
//			fail("O email deve ser composto por @ e .com");
//		} catch (Exception e) {
//			assertEquals("Email inválido!", e.getMessage());
//		}
//	}
//	
//	@Test
//	public void cadastroUsuarioSemParametroSenha() {
//		try {
//			Usuario usuario = new Usuario();
//			usuario.setNome("Elza Soares");
//			usuario.setCpf("85572991090");
//			usuario.setEmail("elza@gmail.com");
//			validator.inicia(usuario);
//			fail("A senha é de preenchimento obrigatório!");
//		} catch (Exception e) {
//			assertEquals("O campo senha é de preenchimento obrigatório!", e.getMessage());
//		}
//	}
//	
//	@Test
//	public void cadastroUsuarioParametroSenhaVazio() {
//		try {
//			Usuario usuario = new Usuario();
//			usuario.setNome("Elza Soares");
//			usuario.setCpf("85572991090");
//			usuario.setEmail("elza@gmail.com");
//			usuario.setSenha("");
//			validator.inicia(usuario);
//			fail("A senha é de preenchimento obrigatório!");
//		} catch (Exception e) {
//			assertEquals("O campo senha é de preenchimento obrigatório!", e.getMessage());
//		}
//	}
//	
//	@Test
//	public void cadastroUsuarioValidaTamanhoMaximoSenha() {
//		try {
//			Usuario usuario = new Usuario();
//			usuario.setNome("Elza Soares");
//			usuario.setCpf("85572991090");
//			usuario.setEmail("elza@gmail.com");
//			usuario.setSenha("123456789");
//			validator.inicia(usuario);
//			fail("A senha deve conter entre 6 e 8 caracteres!");
//		} catch (Exception e) {
//			assertEquals("A senha deve conter entre 6 e 8 caracteres!", e.getMessage());
//		}
//	}
//	
//	@Test
//	public void cadastroUsuarioValidaTamanhoMinimoSenha() {
//		try {
//			Usuario usuario = new Usuario();
//			usuario.setNome("Elza Soares");
//			usuario.setCpf("85572991090");
//			usuario.setEmail("elza@gmail.com");
//			usuario.setSenha("12345");
//			validator.inicia(usuario);
//			fail("A senha deve conter entre 6 e 8 caracteres!");
//		} catch (Exception e) {
//			assertEquals("A senha deve conter entre 6 e 8 caracteres!", e.getMessage());
//		}
//	}
//	
//	@Test
//	public void cadastroUsuarioValidaCPF() {
//		List<String> cpfInvalidos = Arrays.asList("00000000000", "223658", "21067138001","34422358796", "12312312312");
//		cpfInvalidos.forEach(cpf -> {
//			Usuario usuario = new Usuario();
//			usuario.setNome("Elza Soares");
//			usuario.setCpf(cpf);
//			usuario.setEmail("elza@gmail.com");
//			usuario.setSenha("12345");
//			try {
//				validator.inicia(usuario);
//				fail("CPF válido!");
//			} catch (Exception e) {
//				assertEquals("CPF inválido!", e.getMessage());
//			}
//		});
//	}

}
