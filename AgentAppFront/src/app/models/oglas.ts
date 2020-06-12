import { Vozilo } from './vozilo';
export class Oglas {
    id:number;
    vozilo: Vozilo;
    mesto: string;
    cena:number;
    popust:number;
    cenaspopust:number;
    slobodanDo: Date;
    slobodanOd: Date;
    cdw:boolean;
    vozilo_id:number;
}
