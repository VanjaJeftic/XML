import { User } from './user';
import { Zahtev } from './zahtev';
export class ZahtevBundleViewDTO {
    bundleID: number;
    user: User;
    bundleZahtevi: Zahtev[] = [];
}
