export interface Supply {
    id?: number
    moment: string
    odometer: number
    litersFilled: number
    literValueOfFuel: number
    fullTank: boolean
    averageConsumption?: number
    fuel: number
}