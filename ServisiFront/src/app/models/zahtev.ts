import { Izvestaj } from './izvestaj';
import { OglasView } from './oglas-view';
export class Zahtev {
    id: number;
    preuzimanje: string;
    povratak: string;
    oglas: OglasView;
    bundle: boolean;
    podnosilac_id:number;
    status:string;
    izvestaj: Izvestaj;
}
