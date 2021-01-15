export interface Supply {
    id?: number
    moment: string
    odometer: number
    litersFilled: number
    literValuerOfFuel: number
    fullTank: boolean
    averageConsumption?: number
    fuel: number
}