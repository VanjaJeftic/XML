import { VrstaMenjaca } from './vrsta-menjaca';
import { TipGoriva } from './tip-goriva';
import { ModelVozila } from './model-vozila';
import { KlasaVozila } from './klasa-vozila';
import { MarkaVozila } from './marka-vozila';
import { User } from './user';
export class Vozilo {
    user: User;
    markaVozila: MarkaVozila;
    klasaVozila: KlasaVozila;
    modelVozila: ModelVozila;
    tipgoriva: TipGoriva;
    vrstamenjaca: VrstaMenjaca;
    predjeniKm:String;
    brsedistadeca:String;
    id:number;
}
