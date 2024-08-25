# Projeto MyHomework
---
![image](https://github.com/user-attachments/assets/5aa5ac93-04b9-4865-80b6-fe5255ff98dd)


A aplicação desenvolvida fornece o gerenciamento de tarefas diárias lançadas nas disciplinas do curso de Engenharia de Software, que precisam serem cumpridas até a data estabelecida, mapeando facilmente seus status.


---


## Funcionalidades
- O sistema permite que tarefas sejam cadastradas;
- O sistema permite que tarefas sejam alteradas;
- O sistema permite que tarefas sejam consultadas por registros;
- O sistema permite que tarefas sejam consultadas por status;
- O sistema permite que tarefas sejam excluídas;
- O sistema permite que todas as tarefas sejam listadas;
- O sistema permite que apenas dados dos professores sejam listados;
- O sistema permite que apenas dados das disciplinas sejam listados;
- O sistema permite a geração de relatórios.


---


## Tela Inicial 
- Contêm as funções do sistema, incluindo a página de cadastro e listagem dos registros.

![image](https://github.com/user-attachments/assets/f5dd434e-5b14-4761-8e8a-18a3d0b86133)

---
### Tela de consulta por 'status'
- Realiza a busca dos registros indicados através do status escolhido.

![image](https://github.com/user-attachments/assets/1359b70a-69ba-495f-9669-f2d02ec46ffc)

---
### Tela de consulta por registro
- Realiza a busca através do código/id, e retorna o registro indicado.

![image](https://github.com/user-attachments/assets/6f9c8419-bc20-4b12-8ec6-07efcf3e71a8)

---
### Tela de alteração
- Faz a alteração da tarefa através do código do registro.

![image](https://github.com/user-attachments/assets/151965a4-b1d5-45cd-84eb-4308c65af3ae)

---
### Tela de exclusão
- Faz a exclusão da tarefa através do código do registro.

![image](https://github.com/user-attachments/assets/8c84c2f1-9c28-4a92-8a27-376adca4ff2d)

---
### Tela de geração de relatórios
- Gera relatórios  e permite a opção de PDF.

![image](https://github.com/user-attachments/assets/62dcd881-31eb-499a-9c58-5d5e112795c0)

---
### Tela "Professores"
- Permite a busca dos dados dos professores através do email ou permite gerar os dados de todos os professores registrados.

![image](https://github.com/user-attachments/assets/962f4de4-2ccf-4d30-8c39-acb48597f4d8)

---
### Tela "Disciplinas"
- Permite a busca dos dados das disciplinas através do código ou permite gerar os dados de todas as disciplinas registradas.

![image](https://github.com/user-attachments/assets/5d7774af-6af1-4d7d-b628-a08d931a5048)



---



## Instruções
- Baixe a aplicação do GitHub e execute na IDE de preferência (IDEs testadas: IntelliJ e NetBeans).
- Caso não queira criar um banco de dados, foi disponibilizado dentro da aplicação um banco de dados já pronto para uso, hospedado em nuvem pelo site (Tembo)[https://tembo.io/].
- Caso queira criar um banco de dados local: Execute as instruções SQL no pgAdmin 4, uma ferramenta de administração e desenvolvimento para bancos de dados PostgreSQL.
- Dentro do projeto aberto na IDE, verifique se o arquivo BancoD.properties contém as informações que correspondem com o banco de dados criado localmente.
- Caso ocorra algum erro na execução, verifique se o (PostgreSQL JDBC Driver)[https://jdbc.postgresql.org/] foi corretamente inserido no projeto e se as configurações de conexão (URL, usuário, senha, etc.) estão corretas.


---


## Scripts do banco de dados

```sql 
CREATE TABLE public.disciplina (
    id integer NOT NULL,
    disciplina character varying(100) NOT NULL,
    codigo character varying(20) NOT NULL,
    turma character varying(20) NOT NULL
);


CREATE TABLE public.professor (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    paluno character varying(100) NOT NULL,
    disciplina_id integer
);


CREATE TABLE public.tarefa (
    id integer NOT NULL,
    descricao text NOT NULL,
    status character varying(20) NOT NULL,
    prazo character varying(100) NOT NULL,
    professor_id integer
);


CREATE FUNCTION public.atualizar(p_codigo character varying, p_disciplina character varying, p_turma character varying, p_nome character varying, p_email character varying, p_paluno character varying, p_descricao character varying, p_status character varying, p_prazo character varying) RETURNS integer
    LANGUAGE plpgsql
    AS $$
DECLARE
    v_disciplina_id INT;
BEGIN
    UPDATE disciplina
    SET disciplina = p_disciplina, turma = p_turma
    WHERE codigo = p_codigo
    RETURNING id INTO v_disciplina_id;

    IF v_disciplina_id IS NOT NULL THEN
        UPDATE professor
        SET nome = p_nome, email = p_email, paluno = p_paluno
        WHERE professor.disciplina_id = v_disciplina_id;

        UPDATE tarefa
        SET descricao = p_descricao, status = p_status, prazo = p_prazo
        WHERE tarefa.professor_id IN (
            SELECT professor.id FROM professor WHERE professor.disciplina_id = v_disciplina_id
        );
		RETURN 0;
    ELSE
        RAISE NOTICE 'Nenhuma disciplina encontrada com o código especificado.';
		RETURN 7;
    END IF;
END $$;


CREATE FUNCTION public.inserir(text, text, text, text, text, text, text, text, text) RETURNS void
    LANGUAGE sql
    AS $_$DO $$
 DECLARE
	 disciplina_id INT;
	 professor_id INT;
 BEGIN
	 INSERT INTO disciplina (disciplina, codigo, turma) 
	 VALUES ($1, $2, $3) 
	 RETURNING id INTO disciplina_id;
	 INSERT INTO professor (nome, email, paluno, disciplina_id) 
	 VALUES ($4, $5, $6, disciplina_id) 
	 RETURNING id INTO professor_id;
	 INSERT INTO tarefa (descricao, status, prazo, professor_id) 
	 VALUES ($7, $8, $9, professor_id);
 END $$$_$;


CREATE FUNCTION public.inserir(disciplina1 character varying, codigo1 character varying, turma1 character varying, nome1 character varying, email1 character varying, paluno1 character varying, descricao1 character varying, status1 character varying, prazo1 character varying) RETURNS void
    LANGUAGE plpgsql
    AS $$
DECLARE
    disciplina_id INT;
    professor_id INT;
BEGIN
    INSERT INTO disciplina (disciplina, codigo, turma) 
    VALUES (disciplina1, codigo1, turma1) 
    RETURNING id INTO disciplina_id;

    INSERT INTO professor (nome, email, paluno, disciplina_id) 
    VALUES (nome1, email1, paluno1, disciplina_id) 
    RETURNING id INTO professor_id;

    INSERT INTO tarefa (descricao, status, prazo, professor_id) 
    VALUES (descricao1, status1, prazo1, professor_id);
END $$;


CREATE SEQUENCE public.disciplina_id_seq;

ALTER TABLE public.disciplina ALTER COLUMN id SET DEFAULT nextval('public.disciplina_id_seq');

SELECT setval('public.disciplina_id_seq', COALESCE((SELECT MAX(id) FROM public.disciplina), 1), false);


CREATE SEQUENCE public.professor_id_seq;

ALTER TABLE public.professor ALTER COLUMN id SET DEFAULT nextval('public.professor_id_seq');

SELECT setval('public.professor_id_seq', COALESCE((SELECT MAX(id) FROM public.professor), 1), false);


CREATE SEQUENCE public.tarefa_id_seq;

ALTER TABLE public.tarefa ALTER COLUMN id SET DEFAULT nextval('public.tarefa_id_seq');

SELECT setval('public.tarefa_id_seq', COALESCE((SELECT MAX(id) FROM public.tarefa), 1), false);
```


---


## Ferramentas Utilizadas
- [JasperReports](https://community.jaspersoft.com/download-jaspersoft/community-edition/)
- [Netbeans](https://netbeans.apache.org/front/main/download/index.html)
- [PostgreSQL](https://www.postgresql.org/download/windows/)
- [Tembo]( https://tembo.io/)


