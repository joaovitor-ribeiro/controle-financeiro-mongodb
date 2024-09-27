import { Observable } from "rxjs";

export interface FormularioServiceInterface {

  editar(id: number, entrada: any) : Observable<any>;

  inserir(entrada: any) : Observable<any>;

}
