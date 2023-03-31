import { Categoria } from './../categoria/categoria.model';

export interface Ganho {
  id: number;
  descricao: string;
  categoria: Categoria;
  valor: string;
  data: string;
}

export interface GanhoForm {
  id: number;
  descricao: string;
  categoria_id: number;
  valor: string;
  data: string;
}

export interface FiltroGanho {
  descricao: string;
  categorias: number[];
  dataInicial: string;
  dataFinal: string;
}

export interface Pageable {
  sort: Sort;
  offset: number;
  pageNumber: number;
  unpaged: boolean;
  paged: boolean;
}

export interface Sort {
  empty: boolean;
  sorted: boolean;
  unsorted: boolean;
}

export interface PageGanho {
  content: Ganho[];
  pageable: Pageable;
  last: boolean;
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  sort: Sort;
  first: boolean;
  numberOfElements: number;
  empty: boolean;
}

export interface Paginacao {
  page: number;
  size: number;
}
