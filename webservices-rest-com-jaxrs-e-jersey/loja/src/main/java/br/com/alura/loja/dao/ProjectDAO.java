package br.com.alura.loja.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import br.com.alura.loja.modelo.Project;

public class ProjectDAO {
	private static Map<Long, Project> banco = new HashMap<Long, Project>();
    private static AtomicLong contador = new AtomicLong(1);

    static {
        banco.put(1l, new Project(1l, "Minha loja", 2014));
        banco.put(2l, new Project(2l, "Alura", 2012));
    }

    public void adiciona(Project projeto) {
        long id = contador.incrementAndGet();
        projeto.setId(id);
        banco.put(id, projeto);
    }

    public Project busca(Long id) {
        return banco.get(id);
    }

    public Project remove(long id) {
        return banco.remove(id);
    }
}
