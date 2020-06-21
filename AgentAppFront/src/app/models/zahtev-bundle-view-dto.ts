import { UserPodnosilac } from './user-podnosilac';
import { Zahtev } from './zahtev';
export class ZahtevBundleViewDTO {
    bundleID: number;
    user: UserPodnosilac;
    bundleZahtevi: Zahtev[] = [];
}
