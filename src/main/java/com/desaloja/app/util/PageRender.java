package com.desaloja.app.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

/**
 * Realiza el calculo para dibujar la paginación
 * @author josegomezremache
 *
 * @param <T>
 */
public class PageRender<T> {

	private String url;
	private Page<T> page;
	private int totalPaginas;
	private int numeroElementosPorPagina;
	private int paginaActual;
	private List<PageItem> items;

	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.items = new ArrayList<PageItem>();
		numeroElementosPorPagina = page.getSize();
		totalPaginas = page.getTotalPages();
		paginaActual = page.getNumber() + 1;
		int desde, hasta;
		if (totalPaginas <= numeroElementosPorPagina) {
			desde = 1;
			hasta = totalPaginas;
		} else {
			if (paginaActual <= numeroElementosPorPagina / 2) {
				desde = 1;
				hasta = numeroElementosPorPagina;
			} else if (paginaActual >= totalPaginas - numeroElementosPorPagina / 2) {
				desde = totalPaginas - numeroElementosPorPagina;
				hasta = numeroElementosPorPagina;
			} else {
				desde = paginaActual - numeroElementosPorPagina / 2;
				hasta = numeroElementosPorPagina;
			}
		}
		for (int i = 0; i < hasta; i++) {
			items.add(new PageItem(desde + i, paginaActual == desde + i));
		}
	}

	public String getUrl() {
		return url;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public List<PageItem> getItems() {
		return items;
	}

	public boolean isFirst() {
		return page.isFirst();
	}

	public boolean isLast() {
		return page.isLast();
	}

	public boolean isHasNext() {
		return page.hasNext();
	}

	public boolean isHasPrevius() {
		return page.hasPrevious();
	}
}
