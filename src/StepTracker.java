import java.util.Calendar;
import java.util.GregorianCalendar;

class StepTracker {
    public MonthData[] monthData;
    public int planSteps;

    public StepTracker(GregorianCalendar calendar) {
        this.planSteps = 10000;

        monthData = new MonthData[12];
        for (int i = 0; i < monthData.length; i++) {
            calendar.set(Calendar.MONTH, i);
            monthData[i] = new MonthData(calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
        }
    }

    public void setCountStepInDay(int inputMonth, int inputDay, int inputSteps) {
        this.monthData[inputMonth].dayMass[inputDay].step = inputSteps;
        this.monthData[inputMonth].sumSteps();
        System.out.println("Шаги записаны");

    }

    public void setPlanStepInDay(int inputSteps) {
        this.planSteps = inputSteps;
        System.out.println("План по шагам изменен");

    }

    public void printStatistic(int inputMonth, String nameMonth, Converter converter) {
        System.out.println("В месяце " + nameMonth + " всего было пройдено " + this.monthData[inputMonth].stepSum + " шагов");
        System.out.println("Максимальное количество шагов: " + this.monthData[inputMonth].stepMax + " среднее количество шагов за день: " + monthData[inputMonth].stepMidl);
        System.out.println("Пройденная дистанция (в км): " + converter.getLenStep(monthData[inputMonth].stepMax) + " Количество сожжённых килокалорий: " + converter.getСalories(this.monthData[inputMonth].stepSum));
        moreActivityDay(inputMonth, 3);
        for (int i = 0; i < this.monthData[inputMonth].dayMass.length; i++) {
            System.out.println("  День " + (i + 1) + ": " + monthData[inputMonth].dayMass[i].step + " план: " + this.planSteps + " количество км: " + converter.getLenStep(monthData[inputMonth].dayMass[i].step) + " количество калорий: " + converter.getСalories(monthData[inputMonth].dayMass[i].step));
        }
    }

    public void moreActivityDay(int month, int period) {
        int[] bestDay = new int[period];
        int sumBestDay = 0;
        int startBestDay = 0;
        int endBestDay = period - 1;
        int tempSumBestDay = 0;
        // bestDay = setBestDay(bestDay,1, period, month);
        for (int i = 0; i < period; i++) {
            sumBestDay += this.monthData[month].dayMass[i].step;

        }
        tempSumBestDay = sumBestDay;
        for (int i = 1; i < this.monthData[month].dayMass.length - period; i++) {
            tempSumBestDay -= this.monthData[month].dayMass[i - 1].step;
            tempSumBestDay += this.monthData[month].dayMass[i + period].step;
            if (tempSumBestDay > sumBestDay) {
                sumBestDay = tempSumBestDay;
                startBestDay = i;
                endBestDay = i + period;
            }
        }
        System.out.println("Лучший период с " + (startBestDay + 1) + " по " + (endBestDay + 1) + " сумма лучших дней " + sumBestDay);

    }

    class MonthData {

        DayData[] dayMass;
        int stepSum;
        int stepMax;
        double stepMidl;


        public MonthData(int numDay) {
            this.dayMass = new DayData[numDay];
            this.stepSum = 0;
            for (int i = 0; i < numDay; i++) {
                this.dayMass[i] = new DayData(i, 0);
            }
        }

        public void sumSteps() {
            int stepSum = 0;
            int stepMax = 0;

            for (int i = 0; i < this.dayMass.length; i++) {
                int step = this.dayMass[i].step;
                stepSum += step;
                if (stepMax < step) {
                    stepMax = step;
                }
            }
            this.stepSum = stepSum;
            this.stepMax = stepMax;
            this.stepMidl = stepSum / this.dayMass.length;
        }

        class DayData {
            int dayOfMonth;
            public int step;

            public DayData(int dayOfMonth, int step) {
                this.dayOfMonth = dayOfMonth;
                this.step = step;
            }
        }
    }
}
